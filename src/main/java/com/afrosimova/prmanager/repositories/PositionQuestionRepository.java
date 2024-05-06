package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.PositionQuestion;
import com.afrosimova.prmanager.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionQuestionRepository extends JpaRepository<PositionQuestion, Long> {

    @Query("select u from POSITION_QUESTION u " +
            "join Question i on i.questionId = u.question.questionId " +
            "join EMPLOYEE e on u.position.positionId = e.position.positionId " +
            "join EMPLOYEE_SURVEY es on e.employeeId = es.employee.employeeId " +
            "where es.employeeSurveyId = :employeeSurveyId " +
            "ORDER BY u.questionOrder")
    List<PositionQuestion> findPositionQuestionBy(@Param("employeeSurveyId") long employeeSurveyId);
}