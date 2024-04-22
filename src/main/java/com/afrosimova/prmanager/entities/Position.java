package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity(name = "POSITION")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POSITION")
@Access(value = AccessType.FIELD)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POSITION_ID")
    private long positionId;
    @NotEmpty
    @Column(name = "POSITION_NAME")
    private String positionName = "";

}


