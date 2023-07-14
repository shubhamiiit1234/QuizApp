package com.javaprojects.quizapp.controller;

import com.javaprojects.quizapp.model.Question;
import com.javaprojects.quizapp.model.QuestionWrapper;
import com.javaprojects.quizapp.model.Response;
import com.javaprojects.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noOfQues, @RequestParam String title) {
        return quizService.createQuiz(category, noOfQues, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response) {
        return quizService.calculateScore(id, response);
    }
}
