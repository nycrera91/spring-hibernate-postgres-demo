package com.nycrera.springhibernatepostgresdemo.controller;

import com.nycrera.springhibernatepostgresdemo.exception.ResourceNotFound;
import com.nycrera.springhibernatepostgresdemo.model.Question;
import com.nycrera.springhibernatepostgresdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alperen Asa
 * Date: 19.05.2020
 * Time: 15:21
 * Project Name: spring-hibernate-postgres-demo
 */

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @RequestMapping(value = "/question/{questionId}", method = RequestMethod.PUT)
    public Question updateQuestion(@PathVariable Long questionId, @RequestBody Question questionRequest) {

        return questionRepository.findById(questionId)
                    .map(question -> {
                        question.setTitle(questionRequest.getTitle());
                        question.setDescription(questionRequest.getDescription());
                        return questionRepository.save(question);
                    }).orElseThrow(() -> new ResourceNotFound("Question not found with id: " + questionId));
    }

    @RequestMapping(value = "question/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {

        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFound("Question not found with id: " + questionId));
    }



}
