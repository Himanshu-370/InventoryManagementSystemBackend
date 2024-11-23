package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Category;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repository.CategoryRepository;
import com.example.InventoryManagement.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    public List<Product> getProductsByCategory(UUID id) {
        return categoryRepository.findById(id)
                .map(Category::getProducts)
                .orElse(Collections.emptyList());
    }

    public Product addProductToCategory(UUID categoryId, Product product) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            product.setCategory(category);
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Category not found");
        }
    }

    public boolean removeProductFromCategory(UUID categoryId, UUID productId) {
        return categoryRepository.findById(categoryId)
                .flatMap(_ -> productRepository.findById(productId))
                .map(product -> {
                    product.setCategory(null);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}