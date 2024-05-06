package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeSurveyService {
    private final EmployeeSurveyRepository employeeSurveyRepository;

    public List<EmployeeSurvey> findEmployeeSurvey(long userId) {
        return employeeSurveyRepository.findEmployeeSurvey(userId);
    }

}
