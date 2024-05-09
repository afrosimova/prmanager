package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUESTION_ID")
    private long questionId;
    @NotEmpty private String text;
    @NotEmpty private int type;
    @NotEmpty private int answers;
}


