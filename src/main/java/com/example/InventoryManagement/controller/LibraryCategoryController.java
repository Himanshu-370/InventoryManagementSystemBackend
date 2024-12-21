package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.LibraryCategoryDTO;
import com.example.InventoryManagement.dto.LibraryProductDTO;
import com.example.InventoryManagement.model.LibraryCategory;
import com.example.InventoryManagement.model.LibraryProduct;
import com.example.InventoryManagement.service.LibraryCategoryService;
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
public class LibraryCategoryController {

    @Autowired
    private LibraryCategoryService categoryService;

    @GetMapping("/library-categories")
    public ResponseEntity<List<LibraryCategoryDTO>> getAllCategories() {
        try {
            List<LibraryCategoryDTO> categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/library-categories/{id}")
    public ResponseEntity<LibraryCategoryDTO> getCategoryById(@PathVariable UUID id) {
        try {
            return categoryService.getCategoryById(id)
                    .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/library-categories")
    public ResponseEntity<LibraryCategoryDTO> createCategory(@RequestBody LibraryCategory category) {
        try {
            LibraryCategoryDTO newCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/library-categories/{id}")
    public ResponseEntity<LibraryCategoryDTO> updateCategory(@PathVariable UUID id, @RequestBody LibraryCategory category) {
        try {
            LibraryCategoryDTO updatedCategory = categoryService.updateCategory(id, category);
            if (updatedCategory != null) {
                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/library-categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable UUID id) {
        try {
            boolean deleted = categoryService.deleteCategory(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/library-categories/{id}/products")
    public ResponseEntity<List<LibraryProductDTO>> getProductsByCategory(@PathVariable UUID id) {
        try {
            List<LibraryProductDTO> products = categoryService.getProductsByCategory(id);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/library-categories/{id}/products")
    public ResponseEntity<LibraryProductDTO> addProductToCategory(@PathVariable UUID id, @RequestBody LibraryProduct product) {
        try {
            LibraryProductDTO addedProduct = categoryService.addProductToCategory(id, product);
            if (addedProduct != null) {
                return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT method for ("/categories/{id}/products/{productId}")
    @PutMapping("/library-categories/{id}/products/{productId}")
    public ResponseEntity<LibraryProductDTO> updateProductToCategory(@PathVariable UUID id, @PathVariable UUID productId,
            @RequestBody LibraryProduct product) {
        try {
            LibraryProductDTO updatedProduct = categoryService.updateProductToCategory(id, productId, product);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/library-categories/{id}/products/{productId}")
    public ResponseEntity<HttpStatus> removeProductFromCategory(@PathVariable UUID id, @PathVariable UUID productId) {
        try {
            boolean removed = categoryService.removeProductFromCategory(id, productId);
            if (removed) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}