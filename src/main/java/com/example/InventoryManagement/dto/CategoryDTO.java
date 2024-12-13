package com.example.InventoryManagement.dto;

import java.util.List;
import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private List<ProductDTO> products;

    // Default constructor
    public CategoryDTO() {
    }

    // Constructor to initialize fields
    public CategoryDTO(UUID id, String name, String description, boolean active, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.products = products;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}