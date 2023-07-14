package com.javaprojects.quizapp.service;
import com.javaprojects.quizapp.model.Question;
import com.javaprojects.quizapp.model.QuestionWrapper;
import com.javaprojects.quizapp.model.Quiz;
import com.javaprojects.quizapp.model.Response;
import com.javaprojects.quizapp.repository.QuestionRepository;
import com.javaprojects.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int noOfQues, String title) {

        List<Question> questionList = questionRepository.findRandomQuestionByCategory(category, noOfQues);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz added Successfully!!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionList = quiz.get().getQuestionList();
        List<QuestionWrapper> userQuestionList = new ArrayList<>();

        for(Question q: questionList) {
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getQid(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            userQuestionList.add(questionWrapper);
        }

        return new ResponseEntity<>(userQuestionList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(int id, List<Response> response) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionList = quiz.get().getQuestionList();

        int correctAnswers = 0, i = 0;
        for(Response r : response) {
            if(r.getResponse().equals(questionList.get(i).getRightAnswer())) {
                correctAnswers++;
            }
            i++;
        }
        return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
    }
}
