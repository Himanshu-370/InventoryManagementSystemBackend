package com.example.InventoryManagement.model;

import java.util.List;

public class SearchResult<T> {
    private List<T> items;
    private int totalCount;
    private int page;
    private int size;
    private int totalPages;

    // Constructor
    public SearchResult(List<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    // Getters and Setters
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}