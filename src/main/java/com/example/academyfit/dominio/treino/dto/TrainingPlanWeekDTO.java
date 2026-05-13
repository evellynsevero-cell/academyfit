package com.example.academyfit.dominio.treino.dto;

import java.util.List;

public record TrainingPlanWeekDTO(
        Integer weekNumber,
        List<TrainingPlanDayDTO> dayDTOS
) {}
