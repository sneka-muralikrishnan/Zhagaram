package com.tamilculture.controller;

import com.tamilculture.model.QuizQuestion;
import com.tamilculture.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    
    private final QuizService quizService;
    
    @GetMapping
    public String quizHome(Model model) {
        model.addAttribute("categories", QuizQuestion.Category.values());
        model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
        return "quiz/index";
    }
    
    @GetMapping("/start")
    public String startQuiz(@RequestParam(required = false) QuizQuestion.Category category,
                           @RequestParam(required = false) QuizQuestion.Difficulty difficulty,
                           @RequestParam(defaultValue = "10") int questionCount,
                           Model model) {
        List<QuizQuestion> questions;
        
        if (category != null && difficulty != null) {
            questions = quizService.getQuestionsByCategory(category);
            // Filter by difficulty
            questions = questions.stream()
                    .filter(q -> q.getDifficulty() == difficulty)
                    .limit(questionCount)
                    .toList();
        } else if (category != null) {
            questions = quizService.getQuestionsByCategory(category);
            questions = questions.stream().limit(questionCount).toList();
        } else if (difficulty != null) {
            questions = quizService.getQuestionsByDifficulty(difficulty);
            questions = questions.stream().limit(questionCount).toList();
        } else {
            questions = quizService.getRandomQuestions(questionCount);
        }
        
        model.addAttribute("questions", questions);
        return "quiz/quiz";
    }
    
    @GetMapping("/admin")
    public String quizAdmin(Model model) {
        model.addAttribute("questions", quizService.getAllQuestions());
        return "quiz/admin";
    }
    
    @GetMapping("/admin/add")
    public String addQuestionForm(Model model) {
        model.addAttribute("question", new QuizQuestion());
        model.addAttribute("categories", QuizQuestion.Category.values());
        model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
        return "quiz/add";
    }
    
    @PostMapping("/admin/add")
    public String addQuestion(@Valid @ModelAttribute("question") QuizQuestion question, 
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", QuizQuestion.Category.values());
            model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
            return "quiz/add";
        }
        
        quizService.saveQuestion(question);
        return "redirect:/quiz/admin";
    }
    
    @GetMapping("/admin/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", quizService.getQuestionById(id));
        model.addAttribute("categories", QuizQuestion.Category.values());
        model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
        return "quiz/edit";
    }
    
    @PostMapping("/admin/edit/{id}")
    public String editQuestion(@PathVariable Long id, 
                              @Valid @ModelAttribute("question") QuizQuestion question, 
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", QuizQuestion.Category.values());
            model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
            return "quiz/edit";
        }
        
        QuizQuestion existingQuestion = quizService.getQuestionById(id);
        existingQuestion.setQuestionText(question.getQuestionText());
        existingQuestion.setOptions(question.getOptions());
        existingQuestion.setCorrectOptionIndex(question.getCorrectOptionIndex());
        existingQuestion.setExplanation(question.getExplanation());
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setDifficulty(question.getDifficulty());
        
        quizService.saveQuestion(existingQuestion);
        return "redirect:/quiz/admin";
    }
    
    @PostMapping("/admin/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        quizService.deleteQuestion(id);
        return "redirect:/quiz/admin";
    }
}
