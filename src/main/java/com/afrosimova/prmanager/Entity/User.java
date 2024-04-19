package com.afrosimova.prmanager.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        private long userId;
        @NotEmpty private String loginUser = "";
        @NotEmpty private String password = "";
        private boolean isAdmin;
}


