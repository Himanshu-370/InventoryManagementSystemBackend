package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.ProductComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductComponentRepository extends JpaRepository<ProductComponent, UUID> {
    // Additional query methods if needed
}