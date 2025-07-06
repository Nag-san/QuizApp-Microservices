package com.Spring.QuestionService.service;

import com.Spring.QuestionService.dao.QuestionDAO;
import com.Spring.QuestionService.model.Question;
import com.Spring.QuestionService.model.QuestionDTO;
import com.Spring.QuestionService.model.QuestionWrapper;
import com.Spring.QuestionService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public Question mappedToEntity(QuestionDTO dto){
        Question q = new Question();
        q.setQuestionTitle(dto.getQuestionTitle());
        q.setDifficultylevel(dto.getDifficultylevel());
        q.setCategory(dto.getCategory());
        q.setRightAnswer(dto.getRightAnswer());
        q.setOption1(dto.getOption1());
        q.setOption2(dto.getOption2());
        q.setOption3(dto.getOption3());
        q.setOption4(dto.getOption4());
        return q;
    }

    public QuestionDTO mappedToDto(Question q){
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setCategory(q.getCategory());
        dto.setDifficultylevel(q.getDifficultylevel());
        dto.setRightAnswer(q.getRightAnswer());
        dto.setQuestionTitle(q.getQuestionTitle());
        dto.setOption1(q.getOption1());
        dto.setOption2(q.getOption2());
        dto.setOption3(q.getOption3());
        dto.setOption4(q.getOption4());
        return dto;
    }
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try{
            return new ResponseEntity<List<QuestionDTO>>(questionDAO.findAll()
                    .stream().map(this::mappedToDto).toList(), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<QuestionDTO> addQuestion(QuestionDTO q) {
        try{
            return new ResponseEntity<>(mappedToDto(questionDAO.save(mappedToEntity(q))), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new QuestionDTO(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionDTO>> getQuestionByCategory(String search) {
        try{
            return new ResponseEntity<List<QuestionDTO>>((List<QuestionDTO>) questionDAO.findQuestionByCategory(search)
                    .stream().map(this::mappedToDto).toList(), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public QuestionDTO updateQuestion(QuestionDTO q, int id) {
//       if(questionDAO.existsById(id))
//           return questionDAO.updateById(q, id);

       return null;
    }

    public void deleteQuestion(int id) {
        questionDAO.deleteById(id);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, int numQuestions) {
        List<Integer> ids = questionDAO.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionWrapper> questions = questionDAO.findAllById(questionIds)
                .stream()
                .map(question -> new QuestionWrapper(question.getId(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4(),
                        question.getQuestionTitle()))
                .toList();

        return new ResponseEntity<List<QuestionWrapper>>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int i = 0;
        int score = 0;
        for(Response res : responses){
            if (Objects.equals(questionDAO.findById(res.getId())
                    .get().getRightAnswer(), res.getResponse())){
                score++;
            }
        }
        return new ResponseEntity<Integer>(score, HttpStatus.OK);
    }
}
