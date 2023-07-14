package com.javaprojects.quizapp.repository;

import com.javaprojects.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RAND() LIMIT :noOfQues", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int noOfQues);
}
