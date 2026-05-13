package com.example.academyfit.controller;

import com.example.academyfit.dominio.treino.TrainingPlan;
import com.example.academyfit.dominio.treino.dto.TrainingPlanDTO;
import com.example.academyfit.dominio.treino.service.TrainingPlanService;
import com.example.academyfit.dominio.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training-plans")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService service;

    @PostMapping
    public ResponseEntity<TrainingPlan> criar(@RequestBody TrainingPlanDTO dto, Authentication auth){
        Usuario user = (Usuario) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPlan(dto, user));
    }
    @GetMapping
    public ResponseEntity<List<TrainingPlan>> listMyPlans(Authentication auth){
        Usuario user = (Usuario) auth.getPrincipal();
        List<TrainingPlan> plans = service.listByOwner(user.getId());
        return ResponseEntity.ok(plans);
    }
    @PostMapping("/{id}/clone")
    public ResponseEntity<TrainingPlan> clone(@PathVariable Long id, Authentication auth){
        Usuario user = (Usuario) auth.getPrincipal();
        return ResponseEntity.ok(service.clonePlan(id,user));
    }
}
