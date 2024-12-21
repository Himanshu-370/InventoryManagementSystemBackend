package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.ProductFormulation;
import com.example.InventoryManagement.model.LibraryProduct;
import com.example.InventoryManagement.service.FormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<ProductFormulation>> getFormulationsByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok(formulationService.getFormulationsByProductId(productId));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<LibraryProduct> updateFormulations(
            @PathVariable UUID productId,
            @RequestBody List<ProductFormulation> formulations) {
        return ResponseEntity.ok(formulationService.updateFormulations(productId, formulations));
    }
}