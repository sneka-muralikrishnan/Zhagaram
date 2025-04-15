package com.tamilculture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String questionText;
    
    @ElementCollection
    @CollectionTable(name = "quiz_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_text")
    private List<String> options = new ArrayList<>();
    
    private Integer correctOptionIndex;
    
    @Column(columnDefinition = "TEXT")
    private String explanation;
    
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }
    
    public enum Category {
        HISTORY,
        LITERATURE,
        MUSIC,
        DANCE,
        FOOD,
        FESTIVALS,
        GENERAL
    }
}
