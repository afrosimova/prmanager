package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.Question;
import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.repositories.QuestionRepository;
import com.afrosimova.prmanager.repositories.SurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
