package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.ProductFormulation;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.FormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulations")
@CrossOrigin(origins = "*")
public class FormulationController {

    @Autowired
    private FormulationService formulationService;

    @GetMapping
    public ResponseEntity<List<ProductFormulation>> getAllFormulations() {
        return ResponseEntity.ok(formulationService.getAllFormulations());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductFormulation>> getFormulationsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(formulationService.getFormulationsByProductId(productId));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateFormulations(
            @PathVariable Long productId,
            @RequestBody List<ProductFormulation> formulations) {
        return ResponseEntity.ok(formulationService.updateFormulations(productId, formulations));
    }
}