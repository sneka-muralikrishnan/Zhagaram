package com.tamilculture.service;

import com.tamilculture.model.CulturalItem;
import com.tamilculture.repository.CulturalItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CulturalItemService {
    
    private final CulturalItemRepository culturalItemRepository;
    
    public List<CulturalItem> getAllItems() {
        return culturalItemRepository.findAll();
    }
    
    public CulturalItem getItemById(Long id) {
        return culturalItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultural item not found with id: " + id));
    }
    
    public List<CulturalItem> getItemsByCategory(CulturalItem.Category category) {
        return culturalItemRepository.findByCategory(category);
    }
    
    public CulturalItem saveItem(CulturalItem item) {
        return culturalItemRepository.save(item);
    }
    
    public void deleteItem(Long id) {
        culturalItemRepository.deleteById(id);
    }
}
