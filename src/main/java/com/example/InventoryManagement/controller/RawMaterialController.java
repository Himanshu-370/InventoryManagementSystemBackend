package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.RawMaterialDTO;
import com.example.InventoryManagement.model.RawMaterial;
import com.example.InventoryManagement.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @GetMapping("/raw-materials")
    public ResponseEntity<List<RawMaterialDTO>> getAllRawMaterials() {
        List<RawMaterialDTO> rawMaterials = rawMaterialService.getAllRawMaterials().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rawMaterials);
    }

    @GetMapping("/raw-materials/{id}")
    public ResponseEntity<RawMaterialDTO> getRawMaterialById(@PathVariable UUID id) {
        Optional<RawMaterial> rawMaterial = rawMaterialService.getRawMaterialById(id);
        return rawMaterial.map(material -> ResponseEntity.ok(convertToDTO(material)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/raw-materials")
    public ResponseEntity<RawMaterialDTO> createRawMaterial(@RequestBody RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = convertToEntity(rawMaterialDTO);
        RawMaterial createdRawMaterial = rawMaterialService.createRawMaterial(rawMaterial);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdRawMaterial));
    }

    @PutMapping("/raw-materials/{id}")
    public ResponseEntity<RawMaterialDTO> updateRawMaterial(@PathVariable UUID id,
            @RequestBody RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = convertToEntity(rawMaterialDTO);
        Optional<RawMaterial> updatedRawMaterial = rawMaterialService.updateRawMaterial(id, rawMaterial);
        return updatedRawMaterial.map(material -> ResponseEntity.ok(convertToDTO(material)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/raw-materials/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable UUID id) {
        boolean isDeleted = rawMaterialService.deleteRawMaterial(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private RawMaterialDTO convertToDTO(RawMaterial rawMaterial) {
        RawMaterialDTO rawMaterialDTO = new RawMaterialDTO();
        rawMaterialDTO.setId(rawMaterial.getId());
        rawMaterialDTO.setName(rawMaterial.getName());
        rawMaterialDTO.setQuantity(rawMaterial.getQuantity());
        rawMaterialDTO.setUnit(rawMaterial.getUnit());
        rawMaterialDTO.setPrice(rawMaterial.getPrice());
        rawMaterialDTO.setDensity(rawMaterial.getDensity());
        rawMaterialDTO.setPriceAfterConversion(rawMaterial.getPriceAfterConversion());
        rawMaterialDTO.setLastUpdated(rawMaterial.getLastUpdated());
        rawMaterialDTO.setSupplier(rawMaterial.getSupplier());
        rawMaterialDTO.setDescription(rawMaterial.getDescription());
        rawMaterialDTO.setSubcategoryId(rawMaterial.getSubcategoryId());
        return rawMaterialDTO;
    }

    private RawMaterial convertToEntity(RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setId(rawMaterialDTO.getId());
        rawMaterial.setName(rawMaterialDTO.getName());
        rawMaterial.setQuantity(rawMaterialDTO.getQuantity());
        rawMaterial.setUnit(rawMaterialDTO.getUnit());
        rawMaterial.setPrice(rawMaterialDTO.getPrice());
        rawMaterial.setDensity(rawMaterialDTO.getDensity());
        rawMaterial.setPriceAfterConversion(rawMaterialDTO.getPriceAfterConversion());
        rawMaterial.setLastUpdated(rawMaterialDTO.getLastUpdated());
        rawMaterial.setSupplier(rawMaterialDTO.getSupplier());
        rawMaterial.setDescription(rawMaterialDTO.getDescription());
        rawMaterial.setSubcategoryId(rawMaterialDTO.getSubcategoryId());
        return rawMaterial;
    }
}