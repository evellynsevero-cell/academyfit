package com.example.academyfit.dominio.musculo.repository;

import com.example.academyfit.dominio.musculo.model.Musculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusculoRepository extends JpaRepository<Musculo, Long> {
}
