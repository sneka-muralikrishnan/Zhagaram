package com.tamilculture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cultural_items")
public class CulturalItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    
    public enum Category {
        ART,
        MUSIC,
        DANCE,
        LITERATURE,
        FESTIVAL,
        ARCHITECTURE,
        CLOTHING,
        OTHER
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
