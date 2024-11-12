package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {
    private static final String DATA_FILE = "classpath:data/categories.json";
    private final JsonFileService jsonFileService;

    @Autowired
    public CategoryService(JsonFileService jsonFileService) {
        this.jsonFileService = jsonFileService;
    }

    public List<Category> getAllCategories() {
        try {
            log.info("Attempting to read categories from {}", DATA_FILE);
            List<Category> categories = jsonFileService.readFromFile(
                    DATA_FILE,
                    new TypeReference<List<Category>>() {
                    });
            log.info("Successfully loaded {} categories", categories.size());
            return categories;
        } catch (Exception e) {
            log.error("Error reading categories: ", e);
            throw new RuntimeException("Error reading categories: " + e.getMessage());
        }
    }

    public Optional<Category> getCategoryById(Long id) {
        return getAllCategories().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void saveCategory(Category category) {
        List<Category> categories = getAllCategories();
        if (category.getId() == null) {
            category.setId(generateId(categories));
            categories.add(category);
        } else {
            categories.removeIf(c -> c.getId().equals(category.getId()));
            categories.add(category);
        }
        jsonFileService.writeToFile(DATA_FILE, categories);
    }

    private Long generateId(List<Category> categories) {
        return categories.stream()
                .mapToLong(Category::getId)
                .max()
                .orElse(0) + 1;
    }
}