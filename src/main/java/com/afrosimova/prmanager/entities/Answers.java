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
    @Column(name = "ANSWERS_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answersId;
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_SURVEY_ID")
    private EmployeeSurvey employeeSurvey;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
    @Column(name = "ANSWERS_TYPE")
    @NotEmpty private String answersType;
    @Column(name = "ANSWER")
    @NotEmpty private String text = "";
}


