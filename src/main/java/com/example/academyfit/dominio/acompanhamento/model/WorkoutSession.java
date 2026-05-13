package com.example.academyfit.dominio.acompanhamento.model;

import com.example.academyfit.dominio.acompanhamento.dto.ExerciseRecordDTO;
import com.example.academyfit.dominio.acompanhamento.hibertade.ExerciseExecutionRecord;
import com.example.academyfit.dominio.usuario.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_workout_session")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long trainingPlanDayId;

    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "session_id")
    private List<ExerciseExecutionRecord> exercises = new ArrayList<>();

    public WorkoutSession(){
    }

    public WorkoutSession(Long trainingPlanDayId, LocalDate date, Usuario user) {
        this.trainingPlanDayId = trainingPlanDayId;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Long getTrainingPlanDayId() {
        return trainingPlanDayId;
    }

    public void setTrainingPlanDayId(@NotNull Long trainingPlanDayId) {
        this.trainingPlanDayId = trainingPlanDayId;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<ExerciseExecutionRecord> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseExecutionRecord> exercises) {
        this.exercises = exercises;
    }
}
