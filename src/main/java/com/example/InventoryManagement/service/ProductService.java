package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Category;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // public List<Product> searchProducts(String query) {
    // // return productRepository.findByNameContainingIgnoreCase(query);
    // }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product Product) {
        return productRepository.save(Product);
    }

    public Product updateProduct(UUID id, Product Product) {
        if (productRepository.existsById(id)) {
            Product.setId(id);
            return productRepository.save(Product);
        }
        return null;
    }

    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // public void saveProducts(List<Product> products) {
    // productRepository.saveAll(products);
    // }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // public List<Product> findAll() {
    // return productRepository.findAll();
    // }

    // public Optional<Product> findById(UUID id) {
    // return productRepository.findById(id);
    // }
}