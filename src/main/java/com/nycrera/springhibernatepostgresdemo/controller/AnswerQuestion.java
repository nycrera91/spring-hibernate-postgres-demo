package com.nycrera.springhibernatepostgresdemo.controller;

import com.nycrera.springhibernatepostgresdemo.exception.ResourceNotFound;
import com.nycrera.springhibernatepostgresdemo.model.Answer;
import com.nycrera.springhibernatepostgresdemo.repository.AnswerRepository;
import com.nycrera.springhibernatepostgresdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alperen Asa
 * Date: 22.05.2020
 * Time: 11:57
 * Project Name: spring-hibernate-postgres-demo
 */

@RestController
@RequestMapping(value = "/api")
public class AnswerQuestion {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/questions/{questionId}/answers", method = RequestMethod.GET)
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @RequestMapping(value = "/questions/{questionId}/answer", method = RequestMethod.POST)
    public Answer addAnswer(@PathVariable Long questionId, @RequestBody Answer answer) {

        return questionRepository.findById(questionId)
                    .map(question -> {
                        answer.setQuestion(question);
                        return answerRepository.save(answer);
                    }).orElseThrow(() -> new ResourceNotFound("Question not found with id: " + questionId));

    }

    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}", method = RequestMethod.PUT)
    public Answer updateAnswer(@PathVariable Long questionId, @PathVariable Long answerId, @RequestBody Answer answerRequest) {

        if(!questionRepository.existsById(questionId)) {
            throw  new ResourceNotFound("Question not found with id: " + questionId);
        }

        return answerRepository.findById(answerId)
                    .map(answer -> {
                        answer.setText(answerRequest.getText());
                        return answerRepository.save(answer);
                    }).orElseThrow(() -> new ResourceNotFound("Answer not found with id: " + answerId));
    }

    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {

        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFound("Question not found with id: " + questionId);
        }

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFound("Answer not found with id: " + answerId));

    }


}
