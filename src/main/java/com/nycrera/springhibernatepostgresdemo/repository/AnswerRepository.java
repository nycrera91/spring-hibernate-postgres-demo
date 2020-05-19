package com.nycrera.springhibernatepostgresdemo.repository;

import com.nycrera.springhibernatepostgresdemo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alperen Asa
 * Date: 19.05.2020
 * Time: 13:23
 * Project Name: spring-hibernate-postgres-demo
 */

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}
