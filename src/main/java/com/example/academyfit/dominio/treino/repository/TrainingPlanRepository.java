package com.example.academyfit.dominio.treino.repository;

import com.example.academyfit.dominio.treino.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
    //Buscar os planos que pertecem a um usuário específico
    List<TrainingPlan> findByDonoId(Long donoId);
}
