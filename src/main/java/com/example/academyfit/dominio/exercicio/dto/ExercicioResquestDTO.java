package com.example.academyfit.dominio.exercicio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;


public record ExercicioResquestDTO (

        @NotBlank(message = "Título é obrigatório")
        String title,
        @NotBlank(message = "COLOQUE A DESCRIÇÃO")
        String description,
        String videoUrl,
        @NotBlank(message = "Categoria é obrigatória")
        String category,
        @NotEmpty(message = "A lista de equipamentos não pode estar vazia")
        List<Long> equipamentosId,
        @NotEmpty(message = "A lista de músculos Primarios não pode estar vazia")
        List<Long> musculosPrimario,
        List<Long>musculosSecundario

){}
