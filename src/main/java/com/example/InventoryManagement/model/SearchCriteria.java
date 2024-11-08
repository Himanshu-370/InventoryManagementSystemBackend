package com.example.InventoryManagement.model;

import java.time.LocalDateTime;
import java.util.List;

public class SearchCriteria {
    private String query;
    private List<Long> categoryIds;
    private Double minPrice;
    private Double maxPrice;
    private List<Long> rawMaterialIds;
    private LocalDateTime lastUpdatedFrom;
    private LocalDateTime lastUpdatedTo;
    private String sortBy;
    private String sortDirection;
    private Integer page;
    private Integer size;
    private Double minQuantity;
    private Double maxQuantity;
    private List<String> units;
    private Double minDensity;
    private Double maxDensity;
    private Boolean inStock;
    private String searchTerm;

    public SearchCriteria(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Long> getRawMaterialIds() {
        return rawMaterialIds;
    }

    public void setRawMaterialIds(List<Long> rawMaterialIds) {
        this.rawMaterialIds = rawMaterialIds;
    }

    public LocalDateTime getLastUpdatedFrom() {
        return lastUpdatedFrom;
    }

    public void setLastUpdatedFrom(LocalDateTime lastUpdatedFrom) {
        this.lastUpdatedFrom = lastUpdatedFrom;
    }

    public LocalDateTime getLastUpdatedTo() {
        return lastUpdatedTo;
    }

    public void setLastUpdatedTo(LocalDateTime lastUpdatedTo) {
        this.lastUpdatedTo = lastUpdatedTo;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Double minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Double getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public Double getMinDensity() {
        return minDensity;
    }

    public void setMinDensity(Double minDensity) {
        this.minDensity = minDensity;
    }

    public Double getMaxDensity() {
        return maxDensity;
    }

    public void setMaxDensity(Double maxDensity) {
        this.maxDensity = maxDensity;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}