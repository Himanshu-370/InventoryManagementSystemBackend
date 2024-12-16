package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.CategoryDTO;
import com.example.InventoryManagement.dto.ProductDTO;
import com.example.InventoryManagement.dto.SubcategoryDTO;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.model.Category;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.repository.CategoryRepository;
import com.example.InventoryManagement.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO);
    }

    public CategoryDTO createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(UUID id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            Category updatedCategory = categoryRepository.save(category);
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

    public List<ProductDTO> getProductsByCategory(UUID id) {
        return categoryRepository.findById(id)
                .map(Category::getProducts)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO addProductToCategory(UUID categoryId, Product product) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            product.setCategory(category);
            Product savedProduct = productRepository.save(product);
            return convertToProductDTO(savedProduct);
        } else {
            throw new EntityNotFoundException("Category not found");
        }
    }

    public ProductDTO updateProductToCategory(UUID categoryId, UUID productId, Product productDetails) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = productRepository.findById(productId)
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

        Product updatedProduct = productRepository.save(product);
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

    private CategoryDTO convertToDTO(Category category) {
        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive(),
                productDTOs);
    }

    private ProductDTO convertToProductDTO(Product product) {
        List<SubcategoryDTO> subcategoryDTOs = product.getSubcategories().stream()
                .map(this::convertToSubcategoryDTO)
                .collect(Collectors.toList());

        return new ProductDTO(
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

    private SubcategoryDTO convertToSubcategoryDTO(Subcategory subcategory) {
        return new SubcategoryDTO(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getProduct().getId(),
                subcategory.getCategory().getId());
    }
}