package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.repositories.SurveyQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SurveyService {
    private final SurveyQuestionRepository surveyQuestionRepository;

    public List<SurveyQuestion> findPositionQuestionBy(long employeeSurveyId) {
        return surveyQuestionRepository.findSurveyQuestionBy(employeeSurveyId);
    }

}
