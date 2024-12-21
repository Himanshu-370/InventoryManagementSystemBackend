package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.LibraryProductDTO;
import com.example.InventoryManagement.dto.ProductComponentDTO;
import com.example.InventoryManagement.model.LibraryProduct;
import com.example.InventoryManagement.model.ProductComponent;
import com.example.InventoryManagement.service.LibraryProductService;

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
public class LibraryProductController {

    @Autowired
    private LibraryProductService productService;

    @GetMapping("/library-products")
    public ResponseEntity<List<LibraryProductDTO>> getAllProducts() {
        try {
            List<LibraryProductDTO> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/library-products/{id}")
    public ResponseEntity<LibraryProduct> getCategoryById(@PathVariable UUID id) {
        try {
            return productService.getProductById(id)
                    .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/library-products")
    public ResponseEntity<LibraryProductDTO> createProduct(@RequestBody LibraryProduct Product) {
        try {
            LibraryProductDTO newProduct = productService.createProduct(Product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/library-products/{id}")
    public ResponseEntity<LibraryProductDTO> updateProduct(@PathVariable UUID id, @RequestBody LibraryProduct Product) {
        try {
            LibraryProductDTO updatedProduct = productService.updateProduct(id, Product);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/library-products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/library-products/{id}/product-components")
    public ResponseEntity<List<ProductComponentDTO>> getSubcategories(@PathVariable UUID id) {
        try {
            List<ProductComponentDTO> subcategories = productService.getSubcategoriesByProductId(id);
            if (subcategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/library-products/{id}/product-components")
    public ResponseEntity<ProductComponentDTO> addSubcategory(@PathVariable UUID id, @RequestBody ProductComponent subcategory) {
        try {
            ProductComponentDTO newSubcategory = productService.addSubcategoryToProduct(id, subcategory);
            return new ResponseEntity<>(newSubcategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/library-products/{id}/product-components/{componentId}")
    public ResponseEntity<Void> deleteSubcategoryFromProduct(@PathVariable UUID productId,
            @PathVariable UUID subcategoryId) {
        try {
            productService.deleteSubcategoryFromProduct(productId, subcategoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}