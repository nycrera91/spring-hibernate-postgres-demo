package com.nycrera.springhibernatepostgresdemo.repository;

import com.nycrera.springhibernatepostgresdemo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alperen Asa
 * Date: 19.05.2020
 * Time: 13:21
 * Project Name: spring-hibernate-postgres-demo
 */

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
