package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.SubcategoryDTO;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.repository.SubcategoryRepository;
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

    public List<SubcategoryDTO> getAllSubcategories() {
        return subcategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubcategoryDTO> getSubcategoryById(UUID id) {
        return subcategoryRepository.findById(id)
                .map(this::convertToDTO);
    }

    public SubcategoryDTO createSubcategory(Subcategory subcategory) {
        Subcategory savedSubcategory = subcategoryRepository.save(subcategory);
        return convertToDTO(savedSubcategory);
    }

    public SubcategoryDTO updateSubcategory(UUID id, Subcategory subcategory) {
        if (subcategoryRepository.existsById(id)) {
            subcategory.setId(id);
            Subcategory updatedSubcategory = subcategoryRepository.save(subcategory);
            return convertToDTO(updatedSubcategory);
        }
        return null;
    }

    public boolean deleteSubcategory(UUID id) {
        if (subcategoryRepository.existsById(id)) {
            subcategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SubcategoryDTO convertToDTO(Subcategory subcategory) {
        return new SubcategoryDTO(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getProduct().getId(),
                subcategory.getCategory().getId());
    }
}