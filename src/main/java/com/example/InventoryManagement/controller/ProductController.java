package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.ProductDTO;
import com.example.InventoryManagement.dto.SubcategoryDTO;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.model.Subcategory;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    // @GetMapping("/search")
    // public ResponseEntity<List<Product>> searchProducts(@RequestParam String
    // query) {
    // if (query == null || query.trim().isEmpty()) {
    // return ResponseEntity.badRequest().build();
    // }
    // // List<Product> results = productService.searchProducts(query);
    // return ResponseEntity.ok(results);
    // }

    // @GetMapping
    // public ResponseEntity<List<Product>> getAllProducts() {
    // List<Product> products = productService.getAllProducts();
    // return ResponseEntity.ok(products);
    // }

    // @PostMapping
    // public ResponseEntity<Void> saveProducts(@RequestBody List<Product> products)
    // {
    // productService.saveProducts(products);
    // return ResponseEntity.ok().build();
    // }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getCategoryById(@PathVariable UUID id) {
        try {
            return productService.getProductById(id)
                    .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product Product) {
        try {
            ProductDTO newProduct = productService.createProduct(Product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody Product Product) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, Product);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable UUID id) {
        try {
            boolean deleted = productService.deleteProduct(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}/subcategories")
    public ResponseEntity<List<SubcategoryDTO>> getSubcategories(@PathVariable UUID id) {
        try {
            List<SubcategoryDTO> subcategories = productService.getSubcategoriesByProductId(id);
            if (subcategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products/{id}/subcategories")
    public ResponseEntity<SubcategoryDTO> addSubcategory(@PathVariable UUID id, @RequestBody Subcategory subcategory) {
        try {
            SubcategoryDTO newSubcategory = productService.addSubcategoryToProduct(id, subcategory);
            return new ResponseEntity<>(newSubcategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{id}/subcategories/{subcategoryId}")
    public ResponseEntity<HttpStatus> removeSubcategory(@PathVariable UUID id, @PathVariable UUID subcategoryId) {
        try {
            boolean deleted = productService.removeSubcategoryFromProduct(id, subcategoryId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}