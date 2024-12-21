package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, UUID> {
    List<RawMaterial> findBySubcategoryId(UUID subcategoryId);

    Optional<RawMaterial> findByIdAndSubcategoryId(UUID id, UUID subcategoryId);
}
