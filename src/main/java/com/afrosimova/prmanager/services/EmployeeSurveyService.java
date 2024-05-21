package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeSurveyService {
    private final EmployeeSurveyRepository employeeSurveyRepository;

    public List<EmployeeSurvey> findEmployeeSurveys(long userId) {
        return employeeSurveyRepository.findEmployeeSurveys(userId);
    }

    public List<EmployeeSurvey> findManagerSurveys(long userId) {
        return employeeSurveyRepository.findManagerSurveys(userId);
    }

    public EmployeeSurvey findById(Long employeeSurveyId) {
        return employeeSurveyRepository.findById(employeeSurveyId).orElse(null);
    }

    public void finilize(AnswersType userType, long employeeSurveyId) {
        switch (userType) {
            case EMPLOYEE:
                employeeSurveyRepository.finilizeEmployee(employeeSurveyId);
                break;
            case MANAGER:
                employeeSurveyRepository.finilizeManager(employeeSurveyId);
                break;
        }
    }
}
