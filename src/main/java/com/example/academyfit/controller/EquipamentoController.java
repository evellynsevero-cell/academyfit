package com.example.academyfit.controller;

import com.example.academyfit.dominio.equipament.dto.EquipamentoDTO;
import com.example.academyfit.dominio.equipament.model.Equipamento;
import com.example.academyfit.dominio.equipament.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    public ResponseEntity<List<Equipamento>>findAll(){
        return ResponseEntity.ok(equipamentoRepository.findAll());
    }
}
