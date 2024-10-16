package com.example.InventoryManagement.InventoryManagementRepositoryLayer;

import com.example.InventoryManagement.InventoryManagementModel.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
