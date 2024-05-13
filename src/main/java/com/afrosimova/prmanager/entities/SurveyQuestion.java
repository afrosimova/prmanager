package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SURVEY_QUESTION")
@Table(name = "SURVEY_QUESTION")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SURVEY_QUESTION_ID")
    private long positionQuestionId;
    @Column(name = "QUESTION_ORDER")
    @NotEmpty private int questionOrder;
    @ManyToOne
    @JoinColumn(name = "POSITION_ID")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "SURVEY_ID")
    private Survey survey;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}


