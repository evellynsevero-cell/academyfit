package com.example.academyfit.dominio.treino.dto;

public record ExerciseSlotDTO(
        Long exerciseId,
        Integer sets,
        Integer reps,
        Double weightKg,
        Integer restSeconds
) {}
