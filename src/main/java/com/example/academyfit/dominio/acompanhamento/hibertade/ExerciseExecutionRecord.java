package com.example.academyfit.dominio.acompanhamento.hibertade;

import jakarta.persistence.*;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ExerciseExecutionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exerciseId;

    @ElementCollection
    @CollectionTable(name = "tb_workout_set_records")
    private List<SetRecord> sets = new ArrayList<>();

    public ExerciseExecutionRecord(){
    }

    public ExerciseExecutionRecord(Long exerciseId, List<SetRecord> sets) {
        this.exerciseId = exerciseId;
        this.sets = sets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<SetRecord> getSets() {
        return sets;
    }

    public void setSets(List<SetRecord> sets) {
        this.sets = sets;
    }
}
