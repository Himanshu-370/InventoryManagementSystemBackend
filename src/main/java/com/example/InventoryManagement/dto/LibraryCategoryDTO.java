package com.example.InventoryManagement.dto;

import java.util.List;
import java.util.UUID;

public class LibraryCategoryDTO {
    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private List<LibraryProductDTO> products;

    // Default constructor
    public LibraryCategoryDTO() {
    }

    // Constructor to initialize fields
    public LibraryCategoryDTO(UUID id, String name, String description, boolean active, List<LibraryProductDTO> products) {
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

    public List<LibraryProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<LibraryProductDTO> products) {
        this.products = products;
    }
}