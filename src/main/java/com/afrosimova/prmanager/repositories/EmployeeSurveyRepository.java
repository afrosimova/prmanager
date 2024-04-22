package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSurveyRepository extends JpaRepository<EmployeeSurvey, Long> {

    @Query("select u from EMPLOYEE_SURVEY u " +
            "join EMPLOYEE i on u.employee = i " +
            "join SURVEY s on u.survey.surveyId = s.surveyId " +
            "where i.user.userId = :userId"
            )
    List<EmployeeSurvey> findEmployeeSurvey(@Param("userId") long userId);
}