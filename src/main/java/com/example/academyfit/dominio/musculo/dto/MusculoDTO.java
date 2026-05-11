package com.example.academyfit.dominio.musculo.dto;

import com.example.academyfit.dominio.musculo.model.Musculo;

public record MusculoDTO(
        Long id,
        String name,
        String nameEn
)
{
    public MusculoDTO(Musculo entity){
        this(
                entity.getId(),
                entity.getName(),
                entity.getNameEn()
        );
    }
}
