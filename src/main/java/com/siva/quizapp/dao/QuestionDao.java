package com.siva.quizapp.dao;

import com.siva.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findAllByCategoryAndQuestionType(String category, int numQ);
}
