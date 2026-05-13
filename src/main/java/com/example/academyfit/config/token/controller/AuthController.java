package com.example.academyfit.config.token.controller;

import com.example.academyfit.config.token.JwtUtil;
import com.example.academyfit.config.token.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO data){
     //autenticar o email e a senha
      var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
      var auth = this.authenticationManager.authenticate(usernamePassword);

      //Se deu certo tem que gerar o token
      var token = jwtUtil.generateToken(data.email());

      return ResponseEntity.ok(token);
    }
}
