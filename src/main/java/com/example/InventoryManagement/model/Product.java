package com.example.InventoryManagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String code;
    private String description;
    private Long categoryId;
    private Long formulationId;
    private double unitPrice;
    private int packSize;
    private String unit;
    private int minimumStock;
    private int reorderPoint;
    private String status;
    private String manufacturer;
    private int expiryPeriod;
    private String storageConditions;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductFormulation> formulations;

    // Default constructor
    public Product() {
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(Long formulationId) {
        this.formulationId = formulationId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    public int getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(int reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getExpiryPeriod() {
        return expiryPeriod;
    }

    public void setExpiryPeriod(int expiryPeriod) {
        this.expiryPeriod = expiryPeriod;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public List<ProductFormulation> getFormulations() {
        return formulations;
    }

    public void setFormulations(List<ProductFormulation> formulations) {
        this.formulations = formulations;
    }
}