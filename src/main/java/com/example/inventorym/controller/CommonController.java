package com.example.inventorym.controller;


import com.example.inventorym.dto.UserRegisterBO;
import com.example.inventorym.service.impl.UserService;
import com.example.inventorym.util.exception.UserWithEmailAlreadyExistException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {

    private final UserService userService;

    public CommonController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        System.out.println(authentication.getName());
        return "index";
    }


    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegisterBO userDto, BindingResult result) throws UserWithEmailAlreadyExistException {
        if (result.hasErrors()) {
            return "register";
        }

        userService.createNewUser(userDto);

        return "redirect:/login";
    }

    @GetMapping("/redirProducts")
    public String redirectProducts(){
        return "redirect:/products";
    }


    @GetMapping("/redirDocuments")
    public String redirectDocument(){
        return "redirect:/documents";
    }
}
