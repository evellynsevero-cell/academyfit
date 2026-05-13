package com.example.academyfit.dominio.acompanhamento.service;


import com.example.academyfit.dominio.Personal.dto.PersonalRecordResponse;
import com.example.academyfit.dominio.acompanhamento.dto.WorkoutSessionDTO;
import com.example.academyfit.dominio.acompanhamento.hibertade.ExerciseExecutionRecord;
import com.example.academyfit.dominio.acompanhamento.hibertade.SetRecord;
import com.example.academyfit.dominio.acompanhamento.model.WorkoutSession;
import com.example.academyfit.dominio.acompanhamento.repository.WorkoutSessionRepository;
import com.example.academyfit.dominio.exercicio.repository.ExercicioRepository;
import com.example.academyfit.dominio.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutSessionService {
    @Autowired
    private WorkoutSessionRepository repository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Transactional
    public void saveSession(WorkoutSessionDTO dto, Usuario user) {
        // 1. Criar a sessão usando o construtor que acabamos de definir
        WorkoutSession session = new WorkoutSession(
                dto.trainingPlanDayId(),
                dto.date(),
                user
        );

        // 2. Mapear o que veio do DTO para as classes @Embeddable do banco
        dto.exercises().forEach(exDto -> {
            ExerciseExecutionRecord record = new ExerciseExecutionRecord();
            record.setExerciseId(exDto.exerciseId());

            exDto.sets().forEach(setDto -> {
                // Criando o registro de cada série (reps e peso)
                SetRecord set = new SetRecord(setDto.reps(), setDto.weightKg());
                record.getSets().add(set);
            });

            session.getExercises().add(record);
        });

        repository.save(session);
    }

    @Transactional(readOnly = true)
    public PersonalRecordResponse calculatePR(Long exerciseId, Long userId) {
        // Busca o histórico de treinos desse usuário
        List<WorkoutSession> sessions = repository.findAllByUserId(userId);

        Double maxWeight = 0.0;
        Integer maxReps = 0;
        java.time.LocalDate achievedAt = null;

        // Lógica para encontrar o Personal Record (PR)
        for (WorkoutSession session : sessions) {
            for (ExerciseExecutionRecord record : session.getExercises()) {
                if (record.getExerciseId().equals(exerciseId)) {
                    for (SetRecord set : record.getSets()) {
                        if (set.getWeightKg() > maxWeight) {
                            maxWeight = set.getWeightKg();
                            maxReps = set.getReps();
                            achievedAt = session.getDate();
                        }
                    }
                }
            }
        }

        String exerciseTitle = exercicioRepository.findById(exerciseId)
                .map(e -> e.getTitle()) // Puxa o nome do exercício lá do catálogo da Sprint 2
                .orElse("Exercício não encontrado");

        return new PersonalRecordResponse(exerciseId, exerciseTitle, maxWeight, maxReps, achievedAt);
    }
}

