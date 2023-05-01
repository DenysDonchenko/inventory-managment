package com.example.inventorym.repository;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p from Product p where p.user.id =:userId")
    List<Product> findAllByUserId(@Param("userId") UUID userId, Sort sort);

    @Query("SELECT pr FROM Product pr where pr.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    @Query("SELECT pr FROM Product pr WHERE pr.user.id = :userId " +
            "AND pr.name LIKE %:name%")
    List<Product> searchProductsByName(@Param("name") String name, @Param("userId") UUID idUser, Sort sort);
}
