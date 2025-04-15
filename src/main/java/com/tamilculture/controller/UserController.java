package com.tamilculture.controller;

import com.tamilculture.model.User;
import com.tamilculture.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                              BindingResult result, 
                              Model model, 
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        
        try {
            userService.registerNewUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
