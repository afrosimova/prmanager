package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.SurveyQuestion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {

    @Query("select u from SURVEY_QUESTION u " +
            "join Question i on i.questionId = u.question.questionId " +
            "join EMPLOYEE e on u.position.positionId = e.position.positionId " +
            "join EMPLOYEE_SURVEY es on e.employeeId = es.employee.employeeId " +
            "where es.employeeSurveyId = :employeeSurveyId " +
            "and es.survey.surveyId  = u.survey.surveyId " +
            "ORDER BY u.questionOrder")
    List<SurveyQuestion> findSurveyQuestionBy(@Param("employeeSurveyId") long employeeSurveyId);
}