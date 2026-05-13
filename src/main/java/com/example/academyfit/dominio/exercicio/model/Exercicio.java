package com.example.academyfit.dominio.exercicio.model;

import com.example.academyfit.dominio.equipament.model.Equipamento;
import com.example.academyfit.dominio.musculo.model.Musculo;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String videoUrl; //tentar colocar link de verdade

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany
    @JoinTable(name = "TB_EXERCICIO_EQUIPAMENTOS",
            joinColumns = @JoinColumn(name = "exercicio_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
    private Set<Equipamento> equipamentos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "TB_EXERCICIO_MUSCULOS_PRIMARIO",
            joinColumns = @JoinColumn(name = "exercicio_id"),
            inverseJoinColumns = @JoinColumn(name = "musculo_id"))
    private Set<Musculo> musculosPrimario = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "TB_EXERCICIO_MUSCULOS_SECUNDARIO",
            joinColumns = @JoinColumn(name = "exercicio_id"),
            inverseJoinColumns = @JoinColumn(name = "musculo_id"))
    private Set<Musculo> musculosSecundario = new HashSet<>();

    public Exercicio() {
    }

    public Exercicio(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Set<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Set<Musculo> getMusculosPrimario() {
        return musculosPrimario;
    }

    public void setMusculosPrimario(Set<Musculo> musculosPrimario) {
        this.musculosPrimario = musculosPrimario;
    }

    public Set<Musculo> getMusculosSecundario() {
        return musculosSecundario;
    }

    public void setMusculosSecundario(Set<Musculo> musculosSecundario) {
        this.musculosSecundario = musculosSecundario;
    }
}
