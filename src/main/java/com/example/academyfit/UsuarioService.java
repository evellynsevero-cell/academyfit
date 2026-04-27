package com.example.academyfit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
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
}

