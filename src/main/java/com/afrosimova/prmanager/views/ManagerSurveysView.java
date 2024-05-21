package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.MainContentLayout;
import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.security.MyUserPrincipal;
import com.afrosimova.prmanager.security.SecurityService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

import static com.afrosimova.prmanager.entities.AnswersType.MANAGER;

@Route(value = "manager_surveys", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class ManagerSurveysView extends SurveysView {

    public ManagerSurveysView(EmployeeSurveyService employeeSurveyService, SecurityService securityService) {
        super(employeeSurveyService, securityService);
    }

    @Override
    public List<EmployeeSurvey> findSurveys() {
        MyUserPrincipal principal = (MyUserPrincipal) securityService.getAuthenticatedUser();
        return employeeSurveyService.findManagerSurveys(principal.getUser().getUserId());
    }

    @Override
    public AnswersType getUserType() {
        return MANAGER;
    }
}
