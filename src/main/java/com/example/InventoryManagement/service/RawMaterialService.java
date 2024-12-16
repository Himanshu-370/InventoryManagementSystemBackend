package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.RawMaterial;
import com.example.InventoryManagement.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    public Optional<RawMaterial> getRawMaterialById(UUID id) {
        return rawMaterialRepository.findById(id);
    }

    public RawMaterial createRawMaterial(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public Optional<RawMaterial> updateRawMaterial(UUID id, RawMaterial rawMaterial) {
        return rawMaterialRepository.findById(id).map(existingRawMaterial -> {
            existingRawMaterial.setName(rawMaterial.getName());
            existingRawMaterial.setQuantity(rawMaterial.getQuantity());
            existingRawMaterial.setUnit(rawMaterial.getUnit());
            existingRawMaterial.setPrice(rawMaterial.getPrice());
            existingRawMaterial.setDensity(rawMaterial.getDensity());
            existingRawMaterial.setPriceAfterConversion(rawMaterial.getPriceAfterConversion());
            existingRawMaterial.setLastUpdated(rawMaterial.getLastUpdated());
            existingRawMaterial.setSupplier(rawMaterial.getSupplier());
            existingRawMaterial.setDescription(rawMaterial.getDescription());
            existingRawMaterial.setSubcategoryId(rawMaterial.getSubcategoryId());
            return rawMaterialRepository.save(existingRawMaterial);
        });
    }

    public boolean deleteRawMaterial(UUID id) {
        return rawMaterialRepository.findById(id).map(rawMaterial -> {
            rawMaterialRepository.delete(rawMaterial);
            return true;
        }).orElse(false);
    }
}