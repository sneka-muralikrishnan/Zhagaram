package com.tamilculture.controller;

import com.tamilculture.model.CulturalItem;
import com.tamilculture.model.User;
import com.tamilculture.service.CulturalItemService;
import com.tamilculture.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {
    
    private final CulturalItemService culturalItemService;
    private final UserService userService;
    
    @GetMapping
    public String gallery(Model model) {
        model.addAttribute("items", culturalItemService.getAllItems());
        return "gallery/index";
    }
    
    @GetMapping("/{id}")
    public String viewItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", culturalItemService.getItemById(id));
        return "gallery/view";
    }
    
    @GetMapping("/category/{category}")
    public String galleryByCategory(@PathVariable String category, Model model) {
        CulturalItem.Category cat = CulturalItem.Category.valueOf(category.toUpperCase());
        model.addAttribute("items", culturalItemService.getItemsByCategory(cat));
        model.addAttribute("category", cat);
        return "gallery/category";
    }
    
    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("item", new CulturalItem());
        model.addAttribute("categories", CulturalItem.Category.values());
        return "gallery/add";
    }
    
    @PostMapping("/add")
    public String addItem(@Valid @ModelAttribute("item") CulturalItem item, 
                         BindingResult result, 
                         Authentication authentication,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", CulturalItem.Category.values());
            return "gallery/add";
        }
        
        User user = userService.findByUsername(authentication.getName());
        item.setCreatedBy(user);
        
        culturalItemService.saveItem(item);
        return "redirect:/gallery";
    }
    
    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", culturalItemService.getItemById(id));
        model.addAttribute("categories", CulturalItem.Category.values());
        return "gallery/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, 
                          @Valid @ModelAttribute("item") CulturalItem item, 
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", CulturalItem.Category.values());
            return "gallery/edit";
        }
        
        CulturalItem existingItem = culturalItemService.getItemById(id);
        existingItem.setTitle(item.getTitle());
        existingItem.setDescription(item.getDescription());
        existingItem.setImageUrl(item.getImageUrl());
        existingItem.setCategory(item.getCategory());
        
        culturalItemService.saveItem(existingItem);
        return "redirect:/gallery/" + id;
    }
    
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        culturalItemService.deleteItem(id);
        return "redirect:/gallery";
    }
}
