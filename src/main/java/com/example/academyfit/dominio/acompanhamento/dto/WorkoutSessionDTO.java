package com.example.academyfit.dominio.acompanhamento.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record WorkoutSessionDTO(
        @NotNull
        Long trainingPlanDayId,
        @NotNull LocalDate date,
        List<ExerciseRecordDTO> exercises
) {}
