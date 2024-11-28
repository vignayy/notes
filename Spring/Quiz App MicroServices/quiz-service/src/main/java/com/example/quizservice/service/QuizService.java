package com.example.quizservice.service;

import com.example.quizservice.dao.QuizDao;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
//        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).orElse(new Quiz());
        List<Integer> questionList = quiz.getQuestionIds();
        List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromId(questionList).getBody();
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}

