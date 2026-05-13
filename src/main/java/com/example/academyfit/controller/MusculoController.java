package com.example.academyfit.controller;


import com.example.academyfit.dominio.musculo.model.Musculo;
import com.example.academyfit.dominio.musculo.repository.MusculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/musculo")
public class MusculoController {

    @Autowired
    private MusculoRepository musculoRepository;

    @GetMapping
    public ResponseEntity<List<Musculo>>findAll(){
        return ResponseEntity.ok(musculoRepository.findAll());
    }
}
