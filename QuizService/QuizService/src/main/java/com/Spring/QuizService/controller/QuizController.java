package com.Spring.QuizService.controller;

import com.Spring.QuizService.model.*;
import com.Spring.QuizService.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@Valid @RequestBody QuizDto quizDto ){

        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("/getQuizzes")
    public ResponseEntity<List<Quiz>> getQuizzes(){
        return quizService.getQuizzes();
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable("id") int id){
        return quizService.getQuestionsByQuizId(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") int id, @Valid @RequestBody List<Response> response){
        return quizService.calculateResult(id, response);
    }
}
