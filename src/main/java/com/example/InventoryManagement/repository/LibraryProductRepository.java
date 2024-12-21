package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.LibraryProduct;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface LibraryProductRepository extends JpaRepository<LibraryProduct, UUID> {
    // List<Product> findByNameContainingIgnoreCase(String query);
}