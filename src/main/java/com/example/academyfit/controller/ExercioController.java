package com.example.academyfit.controller;

import com.example.academyfit.dominio.exercicio.dto.ExercicioResquestDTO;
import com.example.academyfit.dominio.exercicio.dto.ExerciseResponseDTO;
import com.example.academyfit.dominio.exercicio.service.ExercicioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/exercicio")
public class ExercioController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExercioController.class);

    @Autowired
    private ExercicioService service;

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO>criar(@Valid @RequestBody ExercicioResquestDTO dto){
        logger.info("POST/exercicio - Criando novo exercício: {}", dto.title());
        ExerciseResponseDTO responseDTO = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>>listar(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long equipamentoId,
            @RequestParam(required = false) Long musculoId){

        logger.info("Get/exercicio -Listando exercícios com filtros: category={}, equipmentId={}, muscleId={}");
        List<ExerciseResponseDTO>list = service.findAll(category,equipamentoId, musculoId );
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> buscarPorId(@PathVariable Long id){
        logger.info("GET/ exercicio/{} - Buscando por id", id);
        ExerciseResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO>atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ExercicioResquestDTO dto){
        logger.info("PUT/exercicio/{} - Atualizar dados", id);
        ExerciseResponseDTO responseDTO = service.update(id, dto);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        logger.info("DELETE/exercicio/{} - Removendo exercicio", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}