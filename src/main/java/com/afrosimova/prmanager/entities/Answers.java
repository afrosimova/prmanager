package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity (name = "ANSWERS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ANSWERS")
public class Answers {
    @Id
    @Column(name = "EMPLOYEE_SURVEY_ID")
    private long employeeSurveyId;
    @Id
    @Column(name = "QUESTION_ID")
    private long questionId;
    @Column(name = "ANSWERS_TYPE")
    @NotEmpty private AnswersType answersType;
    @Column(name = "ANSWER")
    @NotEmpty private String text = "";
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}


