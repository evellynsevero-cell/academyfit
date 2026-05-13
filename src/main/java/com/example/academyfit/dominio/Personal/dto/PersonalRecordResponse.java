package com.example.academyfit.dominio.Personal.dto;

import java.time.LocalDate;

public record PersonalRecordResponse(
        Long exerciseId,
        String exerciseTitle,
        Double maxWeightKg,
        Integer repsAtMaxWeight,
        LocalDate achievedAt // Data em que ele bateu o recorde
) {}
