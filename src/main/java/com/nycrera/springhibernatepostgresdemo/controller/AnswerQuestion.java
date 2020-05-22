package com.nycrera.springhibernatepostgresdemo.controller;

import com.nycrera.springhibernatepostgresdemo.model.Answer;
import com.nycrera.springhibernatepostgresdemo.repository.AnswerRepository;
import com.nycrera.springhibernatepostgresdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



}
