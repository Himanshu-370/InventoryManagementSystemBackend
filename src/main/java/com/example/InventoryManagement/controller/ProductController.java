package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.Product;
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
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
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
    public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
        try {
            Product newProduct = productService.createProduct(Product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product Product) {
        try {
            Product updatedProduct = productService.updateProduct(id, Product);
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
}