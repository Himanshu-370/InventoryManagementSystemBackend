package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.LibraryProductDTO;
import com.example.InventoryManagement.dto.ProductComponentDTO;
import com.example.InventoryManagement.model.LibraryProduct;
import com.example.InventoryManagement.model.ProductComponent;
import com.example.InventoryManagement.repository.LibraryProductRepository;
import com.example.InventoryManagement.repository.ProductComponentRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryProductService {

    @Autowired
    private LibraryProductRepository productRepository;

    @Autowired
    private ProductComponentRepository subcategoryRepository;

    public List<LibraryProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LibraryProductDTO createProduct(LibraryProduct product) {
        LibraryProduct savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public LibraryProductDTO updateProduct(UUID id, LibraryProduct product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            LibraryProduct updatedProduct = productRepository.save(product);
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

    public Optional<LibraryProduct> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public LibraryProduct saveProduct(LibraryProduct product) {
        return productRepository.save(product);
    }

    public List<ProductComponentDTO> getSubcategoriesByProductId(UUID productId) {
        return productRepository.findById(productId)
                .map(LibraryProduct::getSubcategories)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToSubcategoryDTO)
                .collect(Collectors.toList());
    }

    public ProductComponentDTO addSubcategoryToProduct(UUID productId, ProductComponent subcategory) {
        Optional<LibraryProduct> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            LibraryProduct product = productOpt.get();
            subcategory.setProduct(product);
            ProductComponent savedSubcategory = subcategoryRepository.save(subcategory);
            return convertToSubcategoryDTO(savedSubcategory);
        } else {
            throw new EntityNotFoundException("Product not found");
        }
    }

    public void deleteSubcategoryFromProduct(UUID productId, UUID subcategoryId) {
        LibraryProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        ProductComponent subcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory not found with ID: " + subcategoryId));

        if (product.getSubcategories().remove(subcategory)) {
            productRepository.save(product);
            subcategoryRepository.delete(subcategory);

        } else {
            throw new EntityNotFoundException("Subcategory not associated with product with ID: " + productId);
        }
    }

    private LibraryProductDTO convertToDTO(LibraryProduct product) {
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