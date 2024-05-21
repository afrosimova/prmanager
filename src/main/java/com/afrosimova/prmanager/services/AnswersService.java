package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.repositories.AnswerRepository;
import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
import com.afrosimova.prmanager.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AnswersService {
    private final AnswerRepository answerRepository;
    private final EmployeeSurveyRepository employeeSurveyRepository;
    private final QuestionRepository questionRepository;

    public List<Answers> findAnswers(long employeeSurveyId, String answersType) {
        return answerRepository.findAnswers(employeeSurveyId, answersType);
    }

    public List<Answers> findAnswers(long employeeSurveyId) {
        return answerRepository.findAnswers(employeeSurveyId);
    }

    public Answers create(long employeeSurveyId, long questionId, String answersType, String value) {
        var employeeSurvey = employeeSurveyRepository.findById(employeeSurveyId).orElseThrow();
        var question = questionRepository.findById(questionId).orElseThrow();
        Answers answers = new Answers(
                null,
                employeeSurvey,
                question,
                answersType,
                value
        );
        return answerRepository.save(answers);
    }

    public void update(Answers answers) {
        answerRepository.save(answers);
    }
}
