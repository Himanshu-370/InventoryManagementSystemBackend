package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.LibraryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LibraryCategoryRepository extends JpaRepository<LibraryCategory, UUID> {
}