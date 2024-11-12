package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.RawMaterial;
import com.example.InventoryManagement.repository.RawMaterialRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RawMaterialService {
    private final String DATA_FILE = "classpath:data/rawmaterials.json";

    @Autowired
    private JsonFileService jsonFileService;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<RawMaterial> getAllRawMaterials() {
        return jsonFileService.readFromFile(DATA_FILE, new TypeReference<List<RawMaterial>>() {
        });
    }

    public Optional<RawMaterial> getRawMaterialById(Long id) {
        return getAllRawMaterials().stream()
                .filter(rm -> rm.getId().equals(id))
                .findFirst();
    }

    public void saveRawMaterial(RawMaterial rawMaterial) {
        List<RawMaterial> materials = getAllRawMaterials();
        if (rawMaterial.getId() == null) {
            rawMaterial.setId(generateId(materials));
            materials.add(rawMaterial);
        } else {
            materials.removeIf(rm -> rm.getId().equals(rawMaterial.getId()));
            materials.add(rawMaterial);
        }
        jsonFileService.writeToFile(DATA_FILE, materials);
    }

    private Long generateId(List<RawMaterial> materials) {
        return materials.stream()
                .mapToLong(RawMaterial::getId)
                .max()
                .orElse(0) + 1;
    }

    public void deleteRawMaterial(Long id) {
        rawMaterialRepository.deleteById(id);
    }
}