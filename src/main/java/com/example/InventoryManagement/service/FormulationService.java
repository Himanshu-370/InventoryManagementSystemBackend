package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.ProductFormulation;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

@Service
public class FormulationService {

    @Autowired
    private ProductService productService;

    @Autowired
    private RawMaterialService rawMaterialService;

    @Autowired
    private JsonFileService jsonFileService;

    private final String DATA_FILE = "classpath:data/formulations.json";

    public List<ProductFormulation> getFormulationsByProductId(UUID productId) {
        return productService.getProductById(productId)
                .map(Product::getFormulations)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product updateFormulations(UUID productId, List<ProductFormulation> formulations) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Validate raw materials exist
        formulations.forEach(f -> {
            rawMaterialService.getRawMaterialById(UUID.fromString(f.getRawMaterialId().toString()))
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Raw material not found: " + f.getRawMaterialId()));
        });

        product.setFormulations(formulations);
        return productService.saveProduct(product);
    }

    public List<ProductFormulation> getAllFormulations() {
        return jsonFileService.readFromFile(DATA_FILE, new TypeReference<List<ProductFormulation>>() {
        });
    }
}