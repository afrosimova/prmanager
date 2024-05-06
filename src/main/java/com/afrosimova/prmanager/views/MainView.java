package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
//import com.afrosimova.prmanager.repositories.PositionQuestionRepository;
import com.afrosimova.prmanager.repositories.PositionQuestionRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("/hello")
@RolesAllowed("ADMIN")
class MainView extends VerticalLayout {
    //private final UserService userService;
    private final PositionQuestionRepository positionQuestionRepository;
    private final EmployeeSurveyRepository employeeSurveyRepository;
    MainView(
            //EmployeeSurveyRepository employeeSurveyRepository
            PositionQuestionRepository positionQuestionRepository, EmployeeSurveyRepository employeeSurveyRepository
            ) {
        this.positionQuestionRepository = positionQuestionRepository;
        this.employeeSurveyRepository = employeeSurveyRepository;
        //add(new H1("Hello, world!"));
        //this.employeeSurveyRepository = employeeSurveyRepository;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        EmployeeSurvey employeeSurvey = EmployeeSurvey.builder()
//                .employeeSurveyId(1)
//                .managerId(1)
//                .empCompleted(true)
//                .manCompleted(false)

        var surveys = employeeSurveyRepository.findEmployeeSurvey(2);
        System.out.println(surveys);

        var survey = positionQuestionRepository.findPositionQuestionBy(1);
        System.out.println(survey);
    }
}