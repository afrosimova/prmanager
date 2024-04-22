package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "POSITION_QUESTION")
@Table(name = "POSITION_QUESTION")
public class PositionQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POSITION_QUESTION_ID")
    private long positionQuestionId;
    @Column(name = "QUESTION_ORDER")
    @NotEmpty private int questionOrder;
    @ManyToOne
    private Position position;
    @ManyToOne
    private Question question;
}


