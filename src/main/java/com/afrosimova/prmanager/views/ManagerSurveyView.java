package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.MainContentLayout;
import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.services.AnswersService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import static com.afrosimova.prmanager.entities.AnswersType.MANAGER;

@Route(value = "manager_survey", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class ManagerSurveyView extends SurveyView {
    public ManagerSurveyView(SurveyService surveyService, AnswersService answersService, EmployeeSurveyService employeeSurveyService) {
        super(surveyService, answersService, employeeSurveyService);
    }

    @Override
    public AnswersType getUserType() {
        return MANAGER;
    }
}
