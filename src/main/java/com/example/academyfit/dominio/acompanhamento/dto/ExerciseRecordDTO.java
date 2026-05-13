package com.example.academyfit.dominio.acompanhamento.dto;

import java.util.List;

public record ExerciseRecordDTO(
        Long exerciseId,
        List<SetRecordDTO> sets
) {}
