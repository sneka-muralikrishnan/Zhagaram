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
@Table(name = "forum_comments")
public class ForumComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private ForumPost post;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
