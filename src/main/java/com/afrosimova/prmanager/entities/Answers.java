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
    //@Column(name = "ANSWERS_TYPE")
    @NotEmpty private enum answersType {};
    @Column(name = "ANSWER")
    @NotEmpty private String text = "";
    @ManyToOne
    private Question question;
}


