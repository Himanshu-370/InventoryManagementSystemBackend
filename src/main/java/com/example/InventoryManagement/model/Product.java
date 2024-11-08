package com.example.InventoryManagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    private Long id;

    private String name;

    private String description;

    private String sku;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductFormulation> formulations;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<ProductFormulation> getFormulations() {
        return formulations;
    }

    public void setFormulations(List<ProductFormulation> formulations) {
        this.formulations = formulations;
    }
}