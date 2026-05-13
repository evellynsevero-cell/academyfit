package com.example.academyfit.controller;

import com.example.academyfit.dominio.Personal.dto.PersonalRecordResponse;
import com.example.academyfit.dominio.acompanhamento.dto.WorkoutSessionDTO;
import com.example.academyfit.dominio.acompanhamento.service.WorkoutSessionService;
import com.example.academyfit.dominio.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {

    @Autowired
    private WorkoutSessionService service;

    @PostMapping
    public ResponseEntity<Void> salvarTreino(@RequestBody WorkoutSessionDTO dto, Authentication auth) {
        Usuario user = (Usuario) auth.getPrincipal();
        service.saveSession(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/exercise/{id}/personal-record")
    public ResponseEntity<PersonalRecordResponse> verRecorde(@PathVariable Long id, Authentication auth) {
        Usuario user = (Usuario) auth.getPrincipal();
        return ResponseEntity.ok(service.calculatePR(id, user.getId()));
    }
}