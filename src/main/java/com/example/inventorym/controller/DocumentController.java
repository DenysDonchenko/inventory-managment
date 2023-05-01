package com.example.inventorym.controller;

import com.example.inventorym.dto.*;
import com.example.inventorym.facade.DocumentFacade;
import com.example.inventorym.facade.ProductFacade;
import com.example.inventorym.facade.impl.DocumentFacadeImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentFacade documentFacade;
    private final ProductFacade productFacade;
    private final TemplateEngine templateEngine;


    public DocumentController(DocumentFacade documentFacade, ProductFacade productFacade, TemplateEngine templateEngine) {
        this.documentFacade = documentFacade;
        this.productFacade = productFacade;
        this.templateEngine = templateEngine;
    }

    @GetMapping("")
    public String getDocs(@RequestParam(defaultValue = "createdAt") String sort,
                          @RequestParam(defaultValue = "desc") String dir,
                          Model model, Authentication authentication) {

        System.out.println(sort + " - " + dir);
        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var documents = documentFacade.findAllByUser(authentication.getName(), sortOrder);
        var products = productFacade.findAllByUser(authentication.getName(), Sort.by("name").descending());

        model.addAttribute("docs", documents);
/*        model.addAttribute("documents", documents);*/
        model.addAttribute("productsForModal", products);
        model.addAttribute("documentRefusedBO", new DocumentRefusedBO());
        model.addAttribute("documentSearchBo", new DocumentSearchBO());
        model.addAttribute("documentReceptionBO", new DocumentReceptionBO());

        return "docs";
    }

    @PostMapping("/search")
    public String searchProduct(DocumentSearchBO documentSearchBO,
                                @RequestParam(defaultValue = "createdAt") String sort,
                                @RequestParam(defaultValue = "desc") String dir,
                                Model model, Authentication authentication) {
        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var documents = documentFacade.searchDocument(authentication.getName(), sortOrder, documentSearchBO);
        var products = productFacade.findAllByUser(authentication.getName(), Sort.by("name").descending());
        model.addAttribute("docs", documents);
        /*        model.addAttribute("documents", documents);*/
        model.addAttribute("productsForModal", products);
        model.addAttribute("documentRefusedBO", new DocumentRefusedBO());
        model.addAttribute("documentSearchBo", new DocumentSearchBO());
        model.addAttribute("documentReceptionBO", new DocumentReceptionBO());
        return "docs";
    }

    @GetMapping("/refusend/{id}")
    public String refusedProduct(@PathVariable("id") UUID id,Model model) {
        var products = documentFacade.findProductByDocId(id);
        model.addAttribute("productsForModal", products);
        model.addAttribute("documentRefusedBO", new DocumentRefusedBO());
        return "documentReturn";
    }

    @PostMapping("/createRefused")
    public String refusedDocument(DocumentRefusedBO documentRefusedBO,Authentication authentication) {
        documentFacade.createRefused(documentRefusedBO, authentication.getName());
        return "redirect:/documents";
    }
    @PostMapping("/createReception")
    public String receptionDocument(DocumentReceptionBO documentReceptionBO, Authentication authentication) {
        documentFacade.createReception(documentReceptionBO, authentication.getName());
        return "redirect:/documents";
    }

    @GetMapping("/generate/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> exportDoc(@PathVariable("id") UUID id) throws Exception {

        var doc = documentFacade.findDocByIdForExport(id);

        // Create a Thymeleaf context with any necessary variables
        Context context = new Context();

        context.setVariable("document", doc);
        context.setVariable("products", doc.getProductExportBOS());


        // Render the Thymeleaf template into an HTML string
        String htmlContent = templateEngine.process("ExportDoc", context);
        // Generate PDF from HTML content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.getFontResolver().addFont("/static/Roboto-Regular.ttf", com.lowagie.text.pdf.BaseFont.IDENTITY_H, com.lowagie.text.pdf.BaseFont.EMBEDDED);
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();

        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        System.out.println("-========================---------------========================--------------");
        // Return response entity with PDF file content
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

    }
}
