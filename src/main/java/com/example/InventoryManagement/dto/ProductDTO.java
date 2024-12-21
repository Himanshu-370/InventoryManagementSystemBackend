package com.example.InventoryManagement.dto;

import java.util.List;
import java.util.UUID;

public class ProductDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private double unitPrice;
    private int packSize;
    private String unit;
    private int minimumStock;
    private int reorderPoint;
    private String status;
    private String manufacturer;
    private int expiryPeriod;
    private String storageConditions;
    private UUID categoryId;
    private List<SubcategoryDTO> subcategories;

    // Default constructor
    public ProductDTO() {
    }

    // Constructor to initialize fields
    public ProductDTO(UUID id, String name, String code, String description, double unitPrice, int packSize,
            String unit, int minimumStock, int reorderPoint, String status, String manufacturer, int expiryPeriod,
            String storageConditions, UUID categoryId, List<SubcategoryDTO> subcategories) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.packSize = packSize;
        this.unit = unit;
        this.minimumStock = minimumStock;
        this.reorderPoint = reorderPoint;
        this.status = status;
        this.manufacturer = manufacturer;
        this.expiryPeriod = expiryPeriod;
        this.storageConditions = storageConditions;
        this.categoryId = categoryId;
        this.subcategories = subcategories;
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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public List<SubcategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
}