package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.SubcategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    // public List<Product> searchProducts(String query) {
    // // return productRepository.findByNameContainingIgnoreCase(query);
    // }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product Product) {
        return productRepository.save(Product);
    }

    public Product updateProduct(UUID id, Product Product) {
        if (productRepository.existsById(id)) {
            Product.setId(id);
            return productRepository.save(Product);
        }
        return null;
    }

    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // public void saveProducts(List<Product> products) {
    // productRepository.saveAll(products);
    // }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // public List<Product> findAll() {
    // return productRepository.findAll();
    // }

    // public Optional<Product> findById(UUID id) {
    // return productRepository.findById(id);
    // }

    public List<Subcategory> getProductsBySubCategory(UUID id) {
        return productRepository.findById(id)
                .map(Product::getSubcategories)
                .orElse(Collections.emptyList());
    }

    public Subcategory addProductToSubCategory(UUID productId, Subcategory subcategory) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            subcategory.setProduct(product);
            return subcategoryRepository.save(subcategory);
        } else {
            throw new EntityNotFoundException("Subcategory not found");
        }
    }

    public boolean removeSubCategoryFromProduct(UUID productId, UUID subcategoryId) {
        return subcategoryRepository.findById(productId)
                .flatMap(subcategory -> subcategoryRepository.findById(subcategoryId))
                .map(subcategory -> {
                    subcategory.setProduct(null);
                    subcategoryRepository.save(subcategory);
                    return true;
                })
                .orElse(false);
    }

    public List<Subcategory> getSubcategoriesByProductId(UUID productId) {
        return productRepository.findById(productId)
                .map(Product::getSubcategories)
                .orElse(Collections.emptyList());
    }

    public Subcategory addSubcategoryToProduct(UUID productId, Subcategory subcategory) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            subcategory.setProduct(product);
            return subcategoryRepository.save(subcategory);
        } else {
            throw new EntityNotFoundException("Product not found");
        }
    }

    public boolean removeSubcategoryFromProduct(UUID productId, UUID subcategoryId) {
        return subcategoryRepository.findById(subcategoryId)
                .map(subcategory -> {
                    subcategory.setProduct(null);
                    subcategoryRepository.save(subcategory);
                    return true;
                })
                .orElse(false);
    }

}