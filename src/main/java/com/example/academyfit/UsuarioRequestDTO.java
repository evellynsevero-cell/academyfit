package com.example.academyfit;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
        @NotBlank
        private String name;
        @Email
        @NotBlank
        private String email;
        @NotBlank @Size(min = 8)
        private String password;
        @NotNull
        private TrainingLevel trainingLevel;

        public @NotBlank String getName() {
                return name;
        }

        public void setName(@NotBlank String name) {
                this.name = name;
        }

        public @Email @NotBlank String getEmail() {
                return email;
        }

        public void setEmail(@Email @NotBlank String email) {
                this.email = email;
        }

        public @NotBlank @Size(min = 8) String getPassword() {
                return password;
        }

        public void setPassword(@NotBlank @Size(min = 8) String password) {
                this.password = password;
        }

        public @NotNull TrainingLevel getTrainingLevel() {
                return trainingLevel;
        }

        public void setTrainingLevel(@NotNull TrainingLevel trainingLevel) {
                this.trainingLevel = trainingLevel;
        }
}

