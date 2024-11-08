package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static final String DATA_FILE = "data/products.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Object fileLock = new Object();

    public List<Product> findAll() {
        synchronized (fileLock) {

            try {
                return objectMapper.readValue(new File(DATA_FILE), new TypeReference<List<Product>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    public List<Product> searchProducts(String query) {
        synchronized (fileLock) {
                        
                        
            try {
                List<Product> products = objectMapper.readValue(new File(DATA_FILE), new TypeReference<List<Product>>() {
                });
                return products.stream()
                        .filter(product -> product.getName().toLowerCase().contains(query) ||
                                           product.getDescription().toLowerCase().contains(query) ||
                                           product.getSku().toLowerCase().contains(query))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    public void saveAll(List<Product> products) {
        synchronized (fileLock) {
            try {
                objectMapper.writeValue(new File(DATA_FILE), products);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<Product> findById(Long id) {
        synchronized (fileLock) {
            try {
                List<Product> products = findAll();
                return products.stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst();
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    public Product save(Product product) {
        synchronized (fileLock) {
            List<Product> products = findAll();
            if (product.getId() == null) {
                product.setId(generateId(products));
            }
            products.removeIf(p -> p.getId().equals(product.getId()));
            products.add(product);
            saveAll(products);
            return product;
        }
    }

    private Long generateId(List<Product> products) {
        return products.stream()
                .mapToLong(Product::getId)
                .max()
                .orElse(0) + 1;
    }
}