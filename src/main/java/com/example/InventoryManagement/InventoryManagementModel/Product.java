package com.example.InventoryManagement.InventoryManagementModel;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double totalCost;

    @OneToMany
    private List<RawMaterial> rawMaterialsList;

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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<RawMaterial> getRawMaterialsList() {
        return rawMaterialsList;
    }

    public void setRawMaterialsList(List<RawMaterial> rawMaterialsList) {
        this.rawMaterialsList = rawMaterialsList;
    }
}
