package com.example.academyfit.dominio.acompanhamento.dto;

import java.time.LocalDate;

public record PersonalResponse(
        Long exerciseId,
        String exerciseTitle,
        Double maxWeightKg,
        Integer maxReps,
        LocalDate achievedAt
) {}
