package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.entities.AnswersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {

    @Query("select a from ANSWERS a " +
            "where a.employeeSurvey.employeeSurveyId = :employeeSurveyId " +
            "and a.answersType  = :answersType")
    List<Answers> findAnswers(@Param("employeeSurveyId") long employeeSurveyId, @Param("answersType") String answersType);

    @Query("select a from ANSWERS a " +
            "where a.employeeSurvey.employeeSurveyId = :employeeSurveyId")
    List<Answers> findAnswers(@Param("employeeSurveyId") long employeeSurveyId);
}
