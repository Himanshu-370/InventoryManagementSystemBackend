package com.example.InventoryManagement.InventoryManagementControllerLayer;

import com.example.InventoryManagement.InventoryManagementModel.RawMaterial;
import com.example.InventoryManagement.InventoryManagementRepositoryLayer.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @GetMapping
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    @PostMapping
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    @PutMapping("/{id}")
    public RawMaterial updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterial updatedRawMaterial) {
        return rawMaterialRepository.findById(id)
                .map(material -> {
                    material.setName(updatedRawMaterial.getName());
                    material.setQuantity(updatedRawMaterial.getQuantity());
                    material.setPricePerUnit(updatedRawMaterial.getPricePerUnit());
                    material.setUnit(updatedRawMaterial.getUnit());
                    return rawMaterialRepository.save(material);
                })
                .orElseThrow(() -> new RuntimeException("Raw Material not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRawMaterial(@PathVariable Long id) {
        rawMaterialRepository.deleteById(id);
    }
}
