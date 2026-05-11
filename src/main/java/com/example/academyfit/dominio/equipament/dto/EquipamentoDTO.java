package com.example.academyfit.dominio.equipament.dto;

import com.example.academyfit.dominio.equipament.model.Equipamento;

public record EquipamentoDTO(
        Long id,
        String name
) {
    public EquipamentoDTO(Equipamento entity){
        this(
                entity.getId(),
                entity.getName()
        );
    }
}
