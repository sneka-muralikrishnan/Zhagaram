package com.tamilculture.controller;

import com.tamilculture.service.CulturalItemService;
import com.tamilculture.service.FoodService;
import com.tamilculture.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final CulturalItemService culturalItemService;
    private final FoodService foodService;
    private final ForumService forumService;
    
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        // Add featured cultural items
        model.addAttribute("featuredItems", culturalItemService.getAllItems().stream().limit(4).toList());
        
        // Add featured foods
        model.addAttribute("featuredFoods", foodService.getAllFoods().stream().limit(4).toList());
        
        // Add recent forum posts
        model.addAttribute("recentPosts", forumService.getAllPosts().stream().limit(5).toList());
        
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
