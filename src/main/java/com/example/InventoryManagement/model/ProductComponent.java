package com.example.InventoryManagement.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ProductComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private LibraryCategory category;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private LibraryProduct product;

    // Default constructor
    public ProductComponent() {
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

    public LibraryCategory getCategory() {
        return category;
    }

    public void setCategory(LibraryCategory category) {
        this.category = category;
    }

    public LibraryProduct getProduct() {
        return product;
    }

    public void setProduct(LibraryProduct product) {
        this.product = product;
    }
}