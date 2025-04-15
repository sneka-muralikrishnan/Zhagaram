package com.tamilculture.repository;

import com.tamilculture.model.CulturalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CulturalItemRepository extends JpaRepository<CulturalItem, Long> {
    List<CulturalItem> findByCategory(CulturalItem.Category category);
}
