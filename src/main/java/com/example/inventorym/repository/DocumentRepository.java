package com.example.inventorym.repository;

import com.example.inventorym.entity.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    @Query("SELECT d from Document d where d.user.id = :userId")
    List<Document> findAllByUser(@Param("userId") UUID userId, Sort sortOrder);

    boolean existsByDocumentNumber(String documentNumber);

    @Query("SELECT d FROM Document d where d.user.id = :id AND d.documentNumber LIKE %:name%")
    List<Document> searchDocumentByNumber(@Param("name") String name, @Param("id") UUID id, Sort sortOrder);
}
