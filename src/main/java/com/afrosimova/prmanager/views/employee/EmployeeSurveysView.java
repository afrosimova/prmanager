package com.afrosimova.prmanager.views.employee;

import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.security.MyUserPrincipal;
import com.afrosimova.prmanager.security.SecurityService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

import static com.afrosimova.prmanager.entities.AnswersType.EMPLOYEE;

@Route(value = "employee_surveys", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class EmployeeSurveysView extends SurveysView {

    public EmployeeSurveysView(EmployeeSurveyService employeeSurveyService, SecurityService securityService) {
        super(employeeSurveyService, securityService);
    }

    @Override
    public List<EmployeeSurvey> findSurveys() {
        MyUserPrincipal principal = (MyUserPrincipal) securityService.getAuthenticatedUser();
        return employeeSurveyService.findEmployeeSurveys(principal.getUser().getUserId());
    }

    @Override
    public AnswersType getUserType() {
        return EMPLOYEE;
    }
}
