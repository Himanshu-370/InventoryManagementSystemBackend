package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.SubcategoryDTO;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.service.SubcategoryService;

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
@Validated
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories() {
        try {
            List<SubcategoryDTO> subcategories = subcategoryService.getAllSubcategories();
            if (subcategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<SubcategoryDTO> getSubcategoryById(@PathVariable UUID id) {
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

    @PutMapping("/subcategories/{id}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable UUID id,
            @RequestBody Subcategory subcategory) {
        try {
            SubcategoryDTO updatedSubcategory = subcategoryService.updateSubcategory(id, subcategory);
            if (updatedSubcategory != null) {
                return new ResponseEntity<>(updatedSubcategory, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/subcategories/{id}")
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
}