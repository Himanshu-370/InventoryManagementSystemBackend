package com.example.InventoryManagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonFileService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Object fileLock = new Object();

    public <T> List<T> readFromFile(String filePath, TypeReference<List<T>> typeReference) {
        synchronized (fileLock) {
            try {
                ClassPathResource resource = new ClassPathResource(filePath);
                try (InputStream inputStream = resource.getInputStream()) {
                    if (inputStream.available() == 0) {
                        return new ArrayList<>();
                    }
                    return objectMapper.readValue(inputStream, typeReference);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to read from file: " + filePath, e);
            }
        }
    }

    public <T> void writeToFile(String filePath, List<T> data) {
        synchronized (fileLock) {
            try {
                ClassPathResource resource = new ClassPathResource(filePath);
                File file = resource.getFile();
                objectMapper.writeValue(file, data);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write to file: " + filePath, e);
            }
        }
    }
}