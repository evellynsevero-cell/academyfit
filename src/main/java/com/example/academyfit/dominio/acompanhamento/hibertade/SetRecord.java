package com.example.academyfit.dominio.acompanhamento.hibertade;

import jakarta.persistence.Embeddable;

@Embeddable
public class SetRecord {
    private Integer reps;
    private Double weightKg;

    public SetRecord() {}

    public SetRecord(Integer reps, Double weightKg) {
        this.reps = reps;
        this.weightKg = weightKg;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }
}
