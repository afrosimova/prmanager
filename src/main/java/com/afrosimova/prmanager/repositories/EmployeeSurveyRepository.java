package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeSurveyRepository extends JpaRepository<EmployeeSurvey, Long> {

    @Query("select u from EMPLOYEE_SURVEY u " +
            "join EMPLOYEE i on u.employee = i " +
            "join SURVEY s on u.survey.surveyId = s.surveyId " +
            "where i.user.userId = :userId"
            )
    List<EmployeeSurvey> findEmployeeSurveys(@Param("userId") long userId);

    @Query("select u from EMPLOYEE_SURVEY u " +
            "join EMPLOYEE i on u.manager = i " +
            "join SURVEY s on u.survey.surveyId = s.surveyId " +
            "where i.user.userId = :userId"
    )
    List<EmployeeSurvey> findManagerSurveys(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query("update EMPLOYEE_SURVEY es " +
            "set es.empCompleted = TRUE " +
            "where es.employeeSurveyId = :employeeSurveyId"
    )
    void finilizeEmployee(@Param("employeeSurveyId") long employeeSurveyId);

    @Modifying
    @Transactional
    @Query("update EMPLOYEE_SURVEY es " +
            "set es.manCompleted = TRUE " +
            "where es.employeeSurveyId = :employeeSurveyId"
    )
    void finilizeManager(@Param("employeeSurveyId") long employeeSurveyId);
}