package com.example.academyfit.dominio.treino.dto;

import com.example.academyfit.dominio.treino.model.Objetivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TrainingPlanDTO(
     @NotBlank
     String name,
     @NotNull
     Objetivo objetivo,
     @NotNull
     Integer weekCount,
     List<TrainingPlanWeekDTO> weekDTOS

){}
