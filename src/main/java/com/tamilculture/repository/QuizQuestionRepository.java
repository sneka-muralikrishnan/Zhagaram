package com.tamilculture.repository;

import com.tamilculture.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    List<QuizQuestion> findByCategory(QuizQuestion.Category category);
    List<QuizQuestion> findByDifficulty(QuizQuestion.Difficulty difficulty);
    List<QuizQuestion> findByCategoryAndDifficulty(QuizQuestion.Category category, QuizQuestion.Difficulty difficulty);
}
