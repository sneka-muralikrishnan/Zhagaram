package com.tamilculture.service;

import com.tamilculture.model.Food;
import com.tamilculture.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    
    private final FoodRepository foodRepository;
    
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    
    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found with id: " + id));
    }
    
    public List<Food> getFoodsByCategory(Food.Category category) {
        return foodRepository.findByCategory(category);
    }
    
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }
    
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
