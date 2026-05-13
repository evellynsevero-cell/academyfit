package com.example.academyfit.dominio.treino.service;

import com.example.academyfit.dominio.treino.TrainingPlan;
import com.example.academyfit.dominio.treino.dto.TrainingPlanDTO;
import com.example.academyfit.dominio.treino.model.ExerciseSlot;
import com.example.academyfit.dominio.treino.model.TrainingPlanDay;
import com.example.academyfit.dominio.treino.model.TrainingPlanWeek;
import com.example.academyfit.dominio.treino.repository.TrainingPlanRepository;
import com.example.academyfit.dominio.usuario.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository repository;

    //Metodo para clonar um plano existente
    @Transactional
    public TrainingPlan clonePlan(Long originalPlanId, Usuario usuarioAtual) {
        TrainingPlan original = repository.findById(originalPlanId)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        if (!original.isRastreio()) {
            throw new RuntimeException("Este plano é privado e não pode ser clonado.");
        }

        TrainingPlan clone = new TrainingPlan();
        clone.setName(original.getName() + " (Cópia)");
        clone.setObjetivo(original.getObjetivo());
        clone.setWeekCount(original.getWeekCount());
        clone.setDono(usuarioAtual);
        clone.setRastreio(true);

        for (TrainingPlanWeek originalWeek : original.getWeeks()) {
            TrainingPlanWeek newWeek = new TrainingPlanWeek();
            newWeek.setWeekNumber(originalWeek.getWeekNumber());

            for (TrainingPlanDay originalDay : originalWeek.getDays()) {
                TrainingPlanDay newDay = new TrainingPlanDay();
                newDay.setDayOfWeek(originalDay.getDayOfWeek());
                newDay.setSplitFocus(originalDay.getSplitFocus());

                for (ExerciseSlot originalSlot : originalDay.getExercises()) {
                    ExerciseSlot newSlot = new ExerciseSlot();
                    newSlot.setExerciseId(originalSlot.getExerciseId());
                    newSlot.setSets(originalSlot.getSets());
                    newSlot.setReps(originalSlot.getReps());
                    newSlot.setWeightkg(originalSlot.getWeightkg());
                    newSlot.setRestSeconds(originalSlot.getRestSeconds());

                    newDay.addExercise(newSlot);
                }
                newWeek.addDay(newDay);
            }
            clone.addWeeks(newWeek);
        }

        return repository.save(clone);
    }
    @Transactional
    public void deletePlan(Long planId, Long currentUserId) {
        TrainingPlan plan = repository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        if (!plan.getDono().getId().equals(currentUserId)) {
            throw new RuntimeException("Ação não permitida: Você não é o dono deste plano.");
        }
        repository.delete(plan);
    }
    @Transactional
    public TrainingPlan createPlan(TrainingPlanDTO dto, Usuario user) {
        // 1. Cria a entidade principal
        TrainingPlan plan = new TrainingPlan();
        plan.setName(dto.name());
        plan.setObjetivo(dto.objetivo());
        plan.setWeekCount(dto.weekCount());
        plan.setDono(user); // Define o usuário logado como dono
        plan.setRastreio(true);

        // 2. Mapeia as Semanas
        if (dto.weekDTOS() != null) {
            dto.weekDTOS().forEach(weekDto -> {
                TrainingPlanWeek week = new TrainingPlanWeek();
                week.setWeekNumber(weekDto.weekNumber());

                // 3. Mapeia os Dias
                if (weekDto.dayDTOS() != null) {
                    weekDto.dayDTOS().forEach(dayDto -> {
                        TrainingPlanDay day = new TrainingPlanDay();
                        day.setDayOfWeek(dayDto.dayOfWeek());
                        day.setSplitFocus(dayDto.splitFocus());

                        // 4. Mapeia os Slots de Exercício
                        if (dayDto.exercises() != null) {
                            dayDto.exercises().forEach(exDto -> {
                                ExerciseSlot slot = new ExerciseSlot();
                                slot.setExerciseId(exDto.exerciseId());
                                slot.setSets(exDto.sets());
                                slot.setReps(exDto.reps());
                                slot.setWeightkg(exDto.weightKg());
                                slot.setRestSeconds(exDto.restSeconds());

                                day.addExercise(slot);
                            });
                        }
                        week.addDay(day);
                    });
                }
                plan.addWeeks(week);
            });
        }
        return repository.save(plan);
    }
    public List<TrainingPlan> listByOwner(Long userId) {
        return repository.findByDonoId(userId);
    }
}