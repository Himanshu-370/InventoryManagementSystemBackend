package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.ProductFormulation;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Service
public class FormulationService {

    @Autowired
    private ProductService productService;

    @Autowired
    private RawMaterialService rawMaterialService;

    @Autowired
    private JsonFileService jsonFileService;

    private final String DATA_FILE = "data/formulations.json";

    public List<ProductFormulation> getFormulationsByProductId(Long productId) {
        return productService.getProductById(productId)
                .map(Product::getFormulations)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product updateFormulations(Long productId, List<ProductFormulation> formulations) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Validate raw materials exist
        formulations.forEach(f -> {
            rawMaterialService.getRawMaterialById(f.getRawMaterialId())
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