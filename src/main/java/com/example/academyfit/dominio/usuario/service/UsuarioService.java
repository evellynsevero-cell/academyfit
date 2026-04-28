package com.example.academyfit.dominio.usuario.service;


import com.example.academyfit.dominio.usuario.dto.UsuarioRequestDTO;
import com.example.academyfit.dominio.usuario.dto.UsuarioResponseDTO;
import com.example.academyfit.dominio.usuario.exception.EmailAlreadyExistsException;
import com.example.academyfit.dominio.usuario.model.Usuario;
import com.example.academyfit.dominio.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public  void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
    public  UsuarioResponseDTO atualizar(Long id, @Valid UsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Usuário não encontrado"));

        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setTrainingLevel(request.getTrainingLevel());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        usuarioRepository.save(usuario);
        return toResponseDTO(usuario);
    }

    public Usuario salvar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) throws EmailAlreadyExistsException {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        Usuario usuarios = new Usuario();
        usuarios.setName(dto.getName());
        usuarios.setEmail(dto.getEmail());
        usuarios.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuarios.setTrainingLevel(dto.getTrainingLevel());
        usuarioRepository.save(usuarios);
        return toResponseDTO(usuarios);
    }
    private UsuarioResponseDTO toResponseDTO(Usuario usuarios) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuarios.getId());
        dto.setName(usuarios.getName());
        dto.setEmail(usuarios.getEmail());
        dto.setTrainingLevel(usuarios.getTrainingLevel());
        dto.setCreatedAt(usuarios.getCreatedAt());
        return dto;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}

