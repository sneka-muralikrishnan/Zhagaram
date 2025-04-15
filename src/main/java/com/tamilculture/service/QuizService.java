package com.tamilculture.service;

import com.tamilculture.model.QuizQuestion;
import com.tamilculture.repository.QuizQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    
    private final QuizQuestionRepository quizQuestionRepository;
    
    public List<QuizQuestion> getAllQuestions() {
        return quizQuestionRepository.findAll();
    }
    
    public QuizQuestion getQuestionById(Long id) {
        return quizQuestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz question not found with id: " + id));
    }
    
    public List<QuizQuestion> getQuestionsByCategory(QuizQuestion.Category category) {
        return quizQuestionRepository.findByCategory(category);
    }
    
    public List<QuizQuestion> getQuestionsByDifficulty(QuizQuestion.Difficulty difficulty) {
        return quizQuestionRepository.findByDifficulty(difficulty);
    }
    
    public List<QuizQuestion> getRandomQuestions(int count) {
        List<QuizQuestion> allQuestions = quizQuestionRepository.findAll();
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(count).toList();
    }
    
    public QuizQuestion saveQuestion(QuizQuestion question) {
        return quizQuestionRepository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        quizQuestionRepository.deleteById(id);
    }
}
