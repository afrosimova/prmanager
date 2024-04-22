package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMPLOYEE")
@Table(name = "EMPLOYEE")
@Access(value = AccessType.FIELD)
public class Employee {
    @Column(name = "EMPLOYEE_ID")
    @Id
    private long employeeId;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "FIRST_NAME")
    @NotEmpty private String firstName = "";
    @Column(name = "LAST_NAME")
    @NotEmpty private String lastName = "";
    @ManyToOne
    @JoinColumn(name = "POSITION_ID")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    @NotEmpty private Employee manager;
    @Column(name = "EMAIL")
    @NotEmpty private String email = "";
}


