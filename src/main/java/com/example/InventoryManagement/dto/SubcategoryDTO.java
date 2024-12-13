package com.example.InventoryManagement.dto;

import java.util.UUID;

public class SubcategoryDTO {
    private UUID id;
    private String name;
    private UUID productId;
    private UUID categoryId;

    // Default constructor
    public SubcategoryDTO() {
    }

    // Constructor to initialize fields
    public SubcategoryDTO(UUID id, String name, UUID productId, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.categoryId = categoryId;
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

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}