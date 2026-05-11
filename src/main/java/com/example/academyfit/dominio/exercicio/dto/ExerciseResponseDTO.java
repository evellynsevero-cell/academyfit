package com.example.academyfit.dominio.exercicio.dto;

import com.example.academyfit.dominio.equipament.dto.EquipamentoDTO;
import com.example.academyfit.dominio.equipament.model.Equipamento;
import com.example.academyfit.dominio.exercicio.model.Category;
import com.example.academyfit.dominio.exercicio.model.Exercicio;
import com.example.academyfit.dominio.musculo.dto.MusculoDTO;
import com.example.academyfit.dominio.musculo.model.Musculo;

import java.util.List;

public record ExerciseResponseDTO(
        Long id,
        String title,
        String description,
        String videoUrl,
        Category category,
        List <EquipamentoDTO> equipamentosId,
        List<MusculoDTO>musculosPrimario,
        List<MusculoDTO>musculoSecundario
){

    public ExerciseResponseDTO(Exercicio entity) {
        this(
             entity.getId(),
             entity.getTitle(),
             entity.getDescription(),
             entity.getVideoUrl(),
             entity.getCategory(),
              //converter SET<EQUIPAMENTO> para list<EquipamentoDTO>
             entity.getEquipamentos().stream().map(EquipamentoDTO::new).toList(),
               //converte o Set<Musculo> primario
             entity.getMusculosPrimario().stream().map(MusculoDTO::new).toList(),
                //converte o Set<Musculo> secundário
             entity.getMusculosSecundario().stream().map(MusculoDTO::new).toList()
        );
    }
}
