package com.example.InventoryManagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JsonFileService {
    @Autowired
    private ResourceLoader resourceLoader;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public <T> T readFromFile(String path, TypeReference<T> typeReference) {
        try {
            Resource resource = resourceLoader.getResource(path);
            return objectMapper.readValue(resource.getInputStream(), typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + path, e);
        }
    }

    public void writeToFile(String path, Object content) {
        try {
            Resource resource = resourceLoader.getResource(path);
            File file = resource.getFile();
            objectMapper.writeValue(file, content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + path, e);
        }
    }
}