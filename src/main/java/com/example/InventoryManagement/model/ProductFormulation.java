package com.example.InventoryManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_formulations")
public class ProductFormulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private LibraryProduct product;

    private Long rawMaterialId;
    private double quantity;
    private double price;
    private double priceAfterConversion;
    private String notes;

    // Default constructor
    public ProductFormulation() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(Long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceAfterConversion() {
        return priceAfterConversion;
    }

    public void setPriceAfterConversion(double priceAfterConversion) {
        this.priceAfterConversion = priceAfterConversion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}