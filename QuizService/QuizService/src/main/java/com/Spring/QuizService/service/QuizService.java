package com.Spring.QuizService.service;

import com.Spring.QuizService.dao.QuizDAO;
import com.Spring.QuizService.feign.QuizInterface;
import com.Spring.QuizService.model.QuestionWrapper;
import com.Spring.QuizService.model.Quiz;
import com.Spring.QuizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<List<Quiz>> getQuizzes() {
        return new ResponseEntity<List<Quiz>>(quizDAO.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDAO.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(int id) {

        List<Integer> ids = quizDAO.findById(id).get().getQuestionIds();
        return quizInterface.getQuestionsById(ids);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {
        return quizInterface.getScore(response);
    }
}
