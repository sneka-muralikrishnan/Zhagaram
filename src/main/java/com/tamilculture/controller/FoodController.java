package com.tamilculture.controller;

import com.tamilculture.model.Food;
import com.tamilculture.model.User;
import com.tamilculture.service.FoodService;
import com.tamilculture.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {
    
    private final FoodService foodService;
    private final UserService userService;
    
    @GetMapping
    public String foods(Model model) {
        model.addAttribute("foods", foodService.getAllFoods());
        return "foods/index";
    }
    
    @GetMapping("/{id}")
    public String viewFood(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodService.getFoodById(id));
        return "foods/view";
    }
    
    @GetMapping("/category/{category}")
    public String foodsByCategory(@PathVariable String category, Model model) {
        Food.Category cat = Food.Category.valueOf(category.toUpperCase());
        model.addAttribute("foods", foodService.getFoodsByCategory(cat));
        model.addAttribute("category", cat);
        return "foods/category";
    }
    
    @GetMapping("/add")
    public String addFoodForm(Model model) {
        model.addAttribute("food", new Food());
        model.addAttribute("categories", Food.Category.values());
        return "foods/add";
    }
    
    @PostMapping("/add")
    public String addFood(@Valid @ModelAttribute("food") Food food, 
                         BindingResult result, 
                         Authentication authentication,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", Food.Category.values());
            return "foods/add";
        }
        
        User user = userService.findByUsername(authentication.getName());
        food.setCreatedBy(user);
        
        foodService.saveFood(food);
        return "redirect:/foods";
    }
    
    @GetMapping("/edit/{id}")
    public String editFoodForm(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodService.getFoodById(id));
        model.addAttribute("categories", Food.Category.values());
        return "foods/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String editFood(@PathVariable Long id, 
                          @Valid @ModelAttribute("food") Food food, 
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", Food.Category.values());
            return "foods/edit";
        }
        
        Food existingFood = foodService.getFoodById(id);
        existingFood.setName(food.getName());
        existingFood.setDescription(food.getDescription());
        existingFood.setRecipe(food.getRecipe());
        existingFood.setImageUrl(food.getImageUrl());
        existingFood.setCategory(food.getCategory());
        
        foodService.saveFood(existingFood);
        return "redirect:/foods/" + id;
    }
    
    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "redirect:/foods";
    }
}
