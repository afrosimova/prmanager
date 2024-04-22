package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity(name = "EMPLOYEE_SURVEY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE_SURVEY")
@Access(value = AccessType.FIELD)
public class EmployeeSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_SURVEY_ID")
    private long employeeSurveyId;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
    @NotEmpty
    @Column(name = "EMP_COMPLETED")
    private boolean empCompleted;
    @NotEmpty
    @Column(name = "MAN_COMPLETED")
    private boolean manCompleted;
    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID")
    //@Column(name = "EMPLOYEE_ID")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "SURVEY_ID")
    private Survey survey;
}


