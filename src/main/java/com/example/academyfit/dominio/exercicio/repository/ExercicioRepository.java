package com.example.academyfit.dominio.exercicio.repository;

import com.example.academyfit.dominio.exercicio.model.Category;
import com.example.academyfit.dominio.exercicio.model.Exercicio;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    /*DISTINCT - Fundamental pois sem ele o banco de dados retornaria o mesmo exercicio várias vezes
           também garante que cada exercício apareça apenas uma vez na lista
    *\
     */
    @Query("SELECT DISTINCT obj FROM Exercicio obj " +
            "LEFT JOIN obj.equipamentos eq " +
            "LEFT JOIN obj.musculosPrimario pm " +
            "WHERE (:category IS NULL OR obj.category = :category) " +
            "AND (:equipamentosId IS NULL OR eq.id = :equipamentosId) " +
            "AND (:musculosId IS NULL OR pm.id = :musculosId)")
    List<Exercicio> findExercicio (
            @Param("category") Category category,
            @Param("equipamentosId") Long equipamentoId,
            @Param("musculosId") Long musculosId
    );
}
