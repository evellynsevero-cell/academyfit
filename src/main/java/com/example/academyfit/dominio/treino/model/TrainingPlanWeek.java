package com.example.academyfit.dominio.treino.model;

import com.example.academyfit.dominio.treino.TrainingPlan;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TrainingPlanWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer weekNumber;  //plano de treino publico

    @ManyToOne
    @JoinColumn(name = "training_plan_id")
    private TrainingPlan trainingPlan;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<TrainingPlanDay> days = new ArrayList<>();

    public TrainingPlanWeek() {
    }

    public TrainingPlanWeek(Integer weekNumber, TrainingPlan trainingPlan) {
        this.weekNumber = weekNumber;
        this.trainingPlan = trainingPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public void setTrainingPlan(TrainingPlan trainingPlan) {
        this.trainingPlan = trainingPlan;
    }

    public List<TrainingPlanDay> getDays() {
        return days;
    }

    public void setDays(List<TrainingPlanDay> days) {
        this.days = days;
    }
    public void addDay(TrainingPlanDay day){
        days.add(day);
        day.setWeek(this);
    }
}
