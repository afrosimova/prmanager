package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity(name = "SURVEY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SURVEY")
@Access(value = AccessType.FIELD)
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SURVEY_ID")
    private long surveyId;
    @NotEmpty
    @Column(name = "DATE_START")
    private String date = "";
    @NotEmpty
    @Column(name = "SURVEY_NAME")
    private String surveyName = "";
    @Column(name = "DATE_END")
    private String dateEnd ="";
}


