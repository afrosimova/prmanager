package com.afrosimova.prmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity(name = "USER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@Access(value = AccessType.FIELD)
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "USER_ID")
        private long userId;
        @NotEmpty
        @Column(name = "LOGIN_USER")
        private String loginUser = "";
        @NotEmpty
        @Column(name = "PASSWORD")
        private String password = "";
        @Column(name = "IS_ADMIN")
        private boolean isAdmin;
}


