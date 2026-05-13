package com.example.academyfit.dominio.treino;

import com.example.academyfit.dominio.treino.model.Objetivo;
import com.example.academyfit.dominio.treino.model.TrainingPlanWeek;
import com.example.academyfit.dominio.usuario.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_training_plan")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Objetivo objetivo; //Perda de peso, ganho muscular, condicionamento

    @NotNull
    private Integer weekCount; //contagem semanal

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario dono;

    private boolean rastreio = true;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL)
    private List<TrainingPlanWeek> weeks;

    public TrainingPlan(){
    }

    public TrainingPlan(String name, Objetivo objetivo, Integer weekCount, Usuario dono, boolean rastreio) {
        this.name = name;
        this.objetivo = objetivo;
        this.weekCount = weekCount;
        this.dono = dono;
        this.rastreio = rastreio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotNull Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(@NotNull Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public @NotNull Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(@NotNull Integer weekCount) {
        this.weekCount = weekCount;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public boolean isRastreio() {
        return rastreio;
    }

    public void setRastreio(boolean rastreio) {
        this.rastreio = rastreio;
    }

    public List<TrainingPlanWeek> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<TrainingPlanWeek> weeks) {
        this.weeks = weeks;
    }

    public void addWeeks(TrainingPlanWeek week) {
    }
}

