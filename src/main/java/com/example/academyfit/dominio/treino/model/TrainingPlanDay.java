package com.example.academyfit.dominio.treino.model;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TrainingPlanDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; //segunda a domingo

    @Enumerated(EnumType.STRING)
    private SplitFocus splitFocus;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private TrainingPlanWeek week;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSlot> exercises = new ArrayList<>();

    public TrainingPlanDay(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public SplitFocus getSplitFocus() {
        return splitFocus;
    }

    public void setSplitFocus(SplitFocus splitFocus) {
        this.splitFocus = splitFocus;
    }

    public TrainingPlanWeek getWeek() {
        return week;
    }

    public void setWeek(TrainingPlanWeek week) {
        this.week = week;
    }

    public List<ExerciseSlot> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseSlot> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(ExerciseSlot exerciseSlot){
        exercises.add(exerciseSlot);
        exerciseSlot.setDay(this);
    }
}
