package com.example.quizapp.dao;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
