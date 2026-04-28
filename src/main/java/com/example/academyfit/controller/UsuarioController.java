package com.example.academyfit.controller;

import com.example.academyfit.dominio.usuario.service.UsuarioService;
import com.example.academyfit.dominio.usuario.exception.EmailAlreadyExistsException;
import com.example.academyfit.dominio.usuario.dto.UsuarioRequestDTO;
import com.example.academyfit.dominio.usuario.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO response = userService.createUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = userService.listarTodos();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    try {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO request) {
    try {
        UsuarioResponseDTO response = userService.atualizar(id, request);
        return ResponseEntity.ok(response);
    } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}
}



