package com.example.InventoryManagement.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {
    @Value("${app.storage.base-path:classpath:data}")
    private String basePath;

    public String getFullPath(String fileName) {
        return basePath + "/" + fileName;
    }
}