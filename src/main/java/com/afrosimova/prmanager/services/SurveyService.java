package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.PositionQuestion;
import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
import com.afrosimova.prmanager.repositories.PositionQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SurveyService {
    private final PositionQuestionRepository positionQuestionRepository;

    public List<PositionQuestion> findPositionQuestionBy(long employeeSurveyId) {
        return positionQuestionRepository.findPositionQuestionBy(employeeSurveyId);
    }

}
