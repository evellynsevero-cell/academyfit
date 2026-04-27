package com.example.academyfit;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table
public class Usuarios {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password; // armazenar hash

    @Enumerated(EnumType.STRING)
    @NotNull
    private TrainingLevel trainingLevel;

    private LocalDateTime createdAt = LocalDateTime.now();

}

