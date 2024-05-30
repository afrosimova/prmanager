package com.afrosimova.prmanager.views.employee;

import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.services.AnswersService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import static com.afrosimova.prmanager.entities.AnswersType.EMPLOYEE;

@Route(value = "employee_survey", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class EmployeeSurveyView extends SurveyView {
    public EmployeeSurveyView(SurveyService surveyService, AnswersService answersService, EmployeeSurveyService employeeSurveyService) {
        super(surveyService, answersService, employeeSurveyService);
    }

    @Override
    public AnswersType getUserType() {
        return EMPLOYEE;
    }
}
