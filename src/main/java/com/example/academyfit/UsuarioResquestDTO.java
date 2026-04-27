package com.example.academyfit;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResquestDTO {
    public class UserRequestDTO {
        @NotBlank
        private String name;
        @Email
        @NotBlank
        private String email;
        @NotBlank @Size(min = 8)
        private String password;
        @NotNull
        private TrainingLevel trainingLevel;
    }
}
