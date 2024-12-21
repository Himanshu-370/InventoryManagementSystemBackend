package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.ProductFormulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulationRepository extends JpaRepository<ProductFormulation, Long> {
}