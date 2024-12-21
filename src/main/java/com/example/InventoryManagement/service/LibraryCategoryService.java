package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.LibraryCategoryDTO;
import com.example.InventoryManagement.dto.LibraryProductDTO;
import com.example.InventoryManagement.dto.ProductComponentDTO;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.model.LibraryCategory;
import com.example.InventoryManagement.model.LibraryProduct;
import com.example.InventoryManagement.model.ProductComponent;
import com.example.InventoryManagement.repository.LibraryCategoryRepository;
import com.example.InventoryManagement.repository.LibraryProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryCategoryService {

    @Autowired
    private LibraryCategoryRepository categoryRepository;

    @Autowired
    private LibraryProductRepository productRepository;

    public List<LibraryCategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LibraryCategoryDTO> getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO);
    }

    public LibraryCategoryDTO createCategory(LibraryCategory category) {
        LibraryCategory savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public LibraryCategoryDTO updateCategory(UUID id, LibraryCategory category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            LibraryCategory updatedCategory = categoryRepository.save(category);
            return convertToDTO(updatedCategory);
        }
        return null;
    }

    public boolean deleteCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<LibraryProductDTO> getProductsByCategory(UUID id) {
        return categoryRepository.findById(id)
                .map(LibraryCategory::getProducts)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public LibraryProductDTO addProductToCategory(UUID categoryId, LibraryProduct product) {
        Optional<LibraryCategory> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            LibraryCategory category = categoryOpt.get();
            product.setCategory(category);
            LibraryProduct savedProduct = productRepository.save(product);
            return convertToProductDTO(savedProduct);
        } else {
            throw new EntityNotFoundException("Category not found");
        }
    }

    public LibraryProductDTO updateProductToCategory(UUID categoryId, UUID productId, LibraryProduct productDetails) {
        LibraryCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        LibraryProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!product.getCategory().getId().equals(category.getId())) {
            throw new IllegalArgumentException("Product does not belong to the specified category");
        }

        product.setName(productDetails.getName());
        product.setCode(productDetails.getCode());
        product.setDescription(productDetails.getDescription());
        product.setFormulationId(productDetails.getFormulationId());
        product.setUnitPrice(productDetails.getUnitPrice());
        product.setPackSize(productDetails.getPackSize());
        product.setUnit(productDetails.getUnit());
        product.setMinimumStock(productDetails.getMinimumStock());
        product.setReorderPoint(productDetails.getReorderPoint());

        LibraryProduct updatedProduct = productRepository.save(product);
        return convertToProductDTO(updatedProduct);
    }

    public boolean removeProductFromCategory(UUID categoryId, UUID productId) {
        return categoryRepository.findById(categoryId)
                .flatMap(category -> productRepository.findById(productId)
                        .map(product -> {
                            product.setCategory(null);
                            productRepository.save(product);
                            return true;
                        }))
                .orElse(false);
    }

    private LibraryCategoryDTO convertToDTO(LibraryCategory category) {
        List<LibraryProductDTO> productDTOs = category.getProducts().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        return new LibraryCategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive(),
                productDTOs);
    }

    private LibraryProductDTO convertToProductDTO(LibraryProduct product) {
        List<ProductComponentDTO> subcategoryDTOs = product.getSubcategories().stream()
                .map(this::convertToSubcategoryDTO)
                .collect(Collectors.toList());

        return new LibraryProductDTO(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getDescription(),
                product.getUnitPrice(),
                product.getPackSize(),
                product.getUnit(),
                product.getMinimumStock(),
                product.getReorderPoint(),
                product.getStatus(),
                product.getManufacturer(),
                product.getExpiryPeriod(),
                product.getStorageConditions(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                subcategoryDTOs);
    }

    private ProductComponentDTO convertToSubcategoryDTO(ProductComponent subcategory) {
        return new ProductComponentDTO(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getProduct().getId(),
                subcategory.getCategory().getId());
    }
}