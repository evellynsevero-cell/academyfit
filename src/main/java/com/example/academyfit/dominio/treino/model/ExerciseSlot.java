package com.example.academyfit.dominio.treino.model;

import jakarta.persistence.*;

@Entity
public class ExerciseSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exerciseId;

    private Integer sets;
    private Integer reps;
    private Double weightkg;
    private Integer restSeconds;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private TrainingPlanDay day;

    public ExerciseSlot(){
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

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeightkg() {
        return weightkg;
    }

    public void setWeightkg(Double weightkg) {
        this.weightkg = weightkg;
    }

    public Integer getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(Integer restSeconds) {
        this.restSeconds = restSeconds;
    }

    public TrainingPlanDay getDay() {
        return day;
    }

    public void setDay(TrainingPlanDay day) {
        this.day = day;
    }
}
