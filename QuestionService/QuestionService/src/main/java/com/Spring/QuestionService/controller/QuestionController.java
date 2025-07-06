package com.Spring.QuestionService.controller;

import com.Spring.QuestionService.model.Question;
import com.Spring.QuestionService.model.QuestionDTO;
import com.Spring.QuestionService.model.QuestionWrapper;
import com.Spring.QuestionService.model.Response;
import com.Spring.QuestionService.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{search}")
    public ResponseEntity<List<QuestionDTO>> getQuestionByCategory(@PathVariable("search") String search){
        return questionService.getQuestionByCategory(search);
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionDTO> addQuestion(@Valid @RequestBody QuestionDTO q){
        return questionService.addQuestion(q);
    }

    @PutMapping("/update/{id}")
    public QuestionDTO updateQuestion(@RequestBody QuestionDTO q, @PathVariable("id") int id){
        return questionService.updateQuestion(q, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable("id") int id){
        questionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String categoryName, @RequestParam int numQuestions) {
            return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@Valid @RequestBody List<Integer> questionIds){
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@Valid @RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
