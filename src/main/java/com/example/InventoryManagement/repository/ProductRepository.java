package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.JsonFileService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static final String DATA_FILE = "classpath:data/products.json";
    private final JsonFileService jsonFileService;
    private final Object fileLock = new Object();

    @Autowired
    public ProductRepository(JsonFileService jsonFileService) {
        this.jsonFileService = jsonFileService;
    }

    public List<Product> findAll() {
        synchronized (fileLock) {
            try {
                return jsonFileService.readFromFile(DATA_FILE, new TypeReference<List<Product>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    public List<Product> searchProducts(String query) {
        synchronized (fileLock) {
            try {
                List<Product> products = findAll();
                return products.stream()
                        .filter(product -> product.getName().toLowerCase().contains(query) ||
                                product.getDescription().toLowerCase().contains(query) ||
                                product.getCode().toLowerCase().contains(query))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    public void saveAll(List<Product> products) {
        synchronized (fileLock) {
            try {
                jsonFileService.writeToFile(DATA_FILE, products);
            } catch (Exception e) {
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