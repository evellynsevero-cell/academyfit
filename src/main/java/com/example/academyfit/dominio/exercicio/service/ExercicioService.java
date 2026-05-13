package com.example.academyfit.dominio.exercicio.service;

import com.example.academyfit.dominio.equipament.model.Equipamento;
import com.example.academyfit.dominio.equipament.repository.EquipamentoRepository;
import com.example.academyfit.dominio.exercicio.dto.ExercicioResquestDTO;
import com.example.academyfit.dominio.exercicio.dto.ExerciseResponseDTO;
import com.example.academyfit.dominio.exercicio.exception.ResourceNotFoundException;
import com.example.academyfit.dominio.exercicio.model.Category;
import com.example.academyfit.dominio.exercicio.model.Exercicio;
import com.example.academyfit.dominio.exercicio.repository.ExercicioRepository;
import com.example.academyfit.dominio.musculo.model.Musculo;
import com.example.academyfit.dominio.musculo.repository.MusculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    private MusculoRepository musculoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Transactional
    public ExerciseResponseDTO criar(ExercicioResquestDTO dto) {
        Exercicio exercicio = new Exercicio();
        copyDtoToEntity(dto, exercicio);
        exercicio = exercicioRepository.save(exercicio);
        return new ExerciseResponseDTO(exercicio);
    }

    @Transactional
    public List<ExerciseResponseDTO> findAll(String category, Long equipamentoId, Long musculoId) {
        // Converte a String recebida da URL para o Enum Category [cite: 73, 83]
        Category cat = (category != null) ? Category.valueOf(category.toUpperCase()) : null;

        // Chama a query customizada com filtros opcionais [cite: 77]
        List<Exercicio> list = exercicioRepository.findExercicio(cat, equipamentoId, musculoId);

        return list.stream().map(ExerciseResponseDTO::new).toList();
    }

    @Transactional
    public ExerciseResponseDTO findById(Long id) {
        // Lança 404 se o exercício não existir
        Exercicio entity = exercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercício não encontrado: " + id));
        return new ExerciseResponseDTO(entity);
    }

    @Transactional
    public ExerciseResponseDTO update(Long id, ExercicioResquestDTO dto) {
        // Busca a referência existente antes de atualizar [cite: 80]
        Exercicio entity = exercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado: " + id));

        copyDtoToEntity(dto, entity);
        entity = exercicioRepository.save(entity);
        return new ExerciseResponseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        // Verifica existência antes de deletar
        if (!exercicioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        }
        exercicioRepository.deleteById(id);
    }

    //Método auxiliar para evitar duplicação de código entre criar e atualizar
    private void copyDtoToEntity(ExercicioResquestDTO dto, Exercicio entity) {
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setVideoUrl(dto.videoUrl());
        entity.setCategory(Category.valueOf(dto.category().toUpperCase()));

        // Limpa as coleções para evitar duplicatas em atualizações
        entity.getEquipamentos().clear();
        entity.getMusculosPrimario().clear();
        entity.getMusculosSecundario().clear();

        // Mapeia IDs para Entidades Reais
        dto.equipamentosId().forEach(id -> {
            Equipamento eq = equipamentoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado: " + id));
            entity.getEquipamentos().add(eq);
        });

        dto.musculosPrimario().forEach(id -> {
            Musculo m = musculoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Músculo primário não encontrado: " + id));
            entity.getMusculosPrimario().add(m);
        });

        if (dto.musculosSecundario() != null) {
            dto.musculosSecundario().forEach(id -> {
                Musculo m = musculoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Músculo secundário não encontrado: " + id));
                entity.getMusculosSecundario().add(m);
            });
        }
    }
}