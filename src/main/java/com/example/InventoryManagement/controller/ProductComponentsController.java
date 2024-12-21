package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.ProductComponentDTO;
import com.example.InventoryManagement.model.RawMaterial;
import com.example.InventoryManagement.model.ProductComponent;
import com.example.InventoryManagement.service.ProductComponentService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Validated
public class ProductComponentsController {

    @Autowired
    private ProductComponentService subcategoryService;

    @GetMapping("/product-components")
    public ResponseEntity<List<ProductComponentDTO>> getAllSubcategories() {
        try {
            List<ProductComponentDTO> subcategories = subcategoryService.getAllSubcategories();
            if (subcategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product-components/{id}")
    public ResponseEntity<ProductComponentDTO> getSubcategoryById(@PathVariable UUID id) {
        try {
            return subcategoryService.getSubcategoryById(id)
                    .map(subcategory -> new ResponseEntity<>(subcategory, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping("/subcategories")
    // public ResponseEntity<SubcategoryDTO> createSubcategory(@RequestBody
    // SubcategoryDTO subcategoryDTO) {
    // try {
    // SubcategoryDTO newSubcategory =
    // subcategoryService.createSubcategory(subcategoryDTO);
    // return new ResponseEntity<>(newSubcategory, HttpStatus.CREATED);
    // } catch (Exception e) {
    // e.printStackTrace();
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PutMapping("/product-components/{id}")
    public ResponseEntity<ProductComponentDTO> updateSubcategory(@PathVariable UUID id,
            @RequestBody ProductComponent subcategory) {
        try {
            ProductComponentDTO updatedSubcategory = subcategoryService.updateSubcategory(id, subcategory);
            if (updatedSubcategory != null) {
                return new ResponseEntity<>(updatedSubcategory, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product-components/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable UUID id) {
        try {
            subcategoryService.deleteSubcategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product-components/{id}/raw-materials")
    public ResponseEntity<List<RawMaterial>> getMaterialsInSubcategory(@PathVariable UUID id) {
        try {
            List<RawMaterial> materials = subcategoryService.getMaterialsInSubcategory(id);
            if (materials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(materials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product-components/{id}/raw-materials")
    public ResponseEntity<RawMaterial> addMaterialToSubcategory(@PathVariable UUID id,
            @RequestBody RawMaterial material) {
        try {
            RawMaterial addedMaterial = subcategoryService.addMaterialToSubcategory(id, material);
            return new ResponseEntity<>(addedMaterial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product-components/{id}/raw-materials/{rawMaterialId}")
    public ResponseEntity<Void> removeMaterialFromSubcategory(@PathVariable UUID id, @PathVariable UUID materialId) {
        try {
            subcategoryService.removeMaterialFromSubcategory(id, materialId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}