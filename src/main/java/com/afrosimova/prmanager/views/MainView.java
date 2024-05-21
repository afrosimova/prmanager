//package com.afrosimova.prmanager.views;
//
//import com.afrosimova.prmanager.repositories.EmployeeRepository;
//import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
////import com.afrosimova.prmanager.repositories.PositionQuestionRepository;
//import com.afrosimova.prmanager.repositories.SurveyQuestionRepository;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.Route;
//import jakarta.annotation.security.RolesAllowed;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Route("/hello")
//@RolesAllowed("ADMIN")
//class MainView extends VerticalLayout {
//    //private final UserService userService;
//    private final SurveyQuestionRepository surveyQuestionRepository;
//    private final EmployeeSurveyRepository employeeSurveyRepository;
//
//    private final EmployeeRepository employeeRepository;
//
//    MainView(
//            //EmployeeSurveyRepository employeeSurveyRepository
//            SurveyQuestionRepository surveyQuestionRepository, EmployeeSurveyRepository employeeSurveyRepository
//            ,EmployeeRepository employeeRepository) {
//        this.surveyQuestionRepository = surveyQuestionRepository;
//        this.employeeSurveyRepository = employeeSurveyRepository;
//        this.employeeRepository = employeeRepository;
//
//        //add(new H1("Hello, world!"));
//        //this.employeeSurveyRepository = employeeSurveyRepository;
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
////        EmployeeSurvey employeeSurvey = EmployeeSurvey.builder()
////                .employeeSurveyId(1)
////                .managerId(1)
////                .empCompleted(true)
////                .manCompleted(false)
//
//        var surveys = employeeSurveyRepository.findEmployeeSurvey(2);
//        System.out.println(surveys);
//
//        var survey = surveyQuestionRepository.findSurveyQuestionBy(1);
//        System.out.println(survey);
//
////        var employee = employeeRepository.findEmployee(1);
////        System.out.println(employee);
//    }
//}