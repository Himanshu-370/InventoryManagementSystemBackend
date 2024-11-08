package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {
    private static final String DATA_FILE = "data/categories.json";
    private final ObjectMapper objectMapper;
    private final JsonFileService jsonFileService;

    @Autowired
    public CategoryService(JsonFileService jsonFileService) {
        this.objectMapper = new ObjectMapper();
        this.jsonFileService = jsonFileService;
    }

    public List<Category> getAllCategories() {
        try {
            log.info("Attempting to read categories from {}", DATA_FILE);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATA_FILE);
            if (inputStream == null) {
                log.error("Cannot find categories.json file");
                throw new RuntimeException("Cannot find categories.json");
            }

            List<Category> categories = objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Category.class));
            log.info("Successfully loaded {} categories", categories.size());
            return categories;
        } catch (IOException e) {
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