package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // List<Product> findByNameContainingIgnoreCase(String query);
}