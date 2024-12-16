package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.SubcategoryDTO;
import com.example.InventoryManagement.model.RawMaterial;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.RawMaterialRepository;
import com.example.InventoryManagement.repository.SubcategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<SubcategoryDTO> getAllSubcategories() {
        return subcategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubcategoryDTO> getSubcategoryById(UUID id) {
        return subcategoryRepository.findById(id)
                .map(this::convertToDTO);
    }

    // public SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO) {
    // Subcategory subcategory = new Subcategory();
    // subcategory.setId(UUID.randomUUID());
    // subcategory.setName(subcategoryDTO.getName());
    // Product product = productRepository.findById(subcategoryDTO.getProductId())
    // .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    // subcategory.setProduct(product);
    // Category category = new Category();
    // category.setId(subcategoryDTO.getCategoryId());
    // subcategory.setCategory(category);
    // return convertToDTO(subcategory);
    // }

    public SubcategoryDTO updateSubcategory(UUID id, Subcategory subcategory) {
        if (subcategoryRepository.existsById(id)) {
            subcategory.setId(id);
            Subcategory updatedSubcategory = subcategoryRepository.save(subcategory);
            return convertToDTO(updatedSubcategory);
        }
        return null;
    }

    public void deleteSubcategory(UUID id) {
        if (!subcategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Subcategory not found with ID: " + id);
        }
        subcategoryRepository.deleteById(id);
    }

    private SubcategoryDTO convertToDTO(Subcategory subcategory) {
        return new SubcategoryDTO(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getProduct().getId(),
                subcategory.getCategory().getId());
    }

    public List<RawMaterial> getMaterialsInSubcategory(UUID subcategoryId) {
        return rawMaterialRepository.findBySubcategoryId(subcategoryId);
    }

    public RawMaterial addMaterialToSubcategory(UUID subcategoryId, RawMaterial material) {
        material.setSubcategoryId(subcategoryId);
        return rawMaterialRepository.save(material);
    }

    public void removeMaterialFromSubcategory(UUID subcategoryId, UUID materialId) {
        Optional<RawMaterial> material = rawMaterialRepository.findByIdAndSubcategoryId(materialId, subcategoryId);
        material.ifPresent(rawMaterialRepository::delete);
    }
}