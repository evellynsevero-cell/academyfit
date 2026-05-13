package com.example.academyfit.dominio.treino.dto;

import com.example.academyfit.dominio.treino.model.SplitFocus;

import java.time.DayOfWeek;
import java.util.List;

public record TrainingPlanDayDTO(
        DayOfWeek dayOfWeek,
        SplitFocus splitFocus,
        List<ExerciseSlotDTO> exercises
) {
}
