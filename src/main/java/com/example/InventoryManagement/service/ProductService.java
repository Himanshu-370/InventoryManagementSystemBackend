package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.ProductDTO;
import com.example.InventoryManagement.dto.SubcategoryDTO;
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
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(UUID id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            return convertToDTO(updatedProduct);
        }
        return null;
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<SubcategoryDTO> getSubcategoriesByProductId(UUID productId) {
        return productRepository.findById(productId)
                .map(Product::getSubcategories)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToSubcategoryDTO)
                .collect(Collectors.toList());
    }

    public SubcategoryDTO addSubcategoryToProduct(UUID productId, Subcategory subcategory) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            subcategory.setProduct(product);
            Subcategory savedSubcategory = subcategoryRepository.save(subcategory);
            return convertToSubcategoryDTO(savedSubcategory);
        } else {
            throw new EntityNotFoundException("Product not found");
        }
    }

    public void deleteSubcategoryFromProduct(UUID productId, UUID subcategoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory not found with ID: " + subcategoryId));

        if (product.getSubcategories().remove(subcategory)) {
            productRepository.save(product);
            subcategoryRepository.delete(subcategory);

        } else {
            throw new EntityNotFoundException("Subcategory not associated with product with ID: " + productId);
        }
    }

    private ProductDTO convertToDTO(Product product) {
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