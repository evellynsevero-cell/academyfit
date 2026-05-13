package com.example.academyfit.dominio.equipament.repository;

import com.example.academyfit.dominio.equipament.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
