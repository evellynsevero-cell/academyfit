package com.example.academyfit.config.token;

import com.example.academyfit.dominio.usuario.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
   @Autowired
   private JwtUtil jwtUtil;

   @Autowired
   private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        System.out.println("HEADER RECEBIDO: " + header); // PRINT 1

        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            boolean eValido = jwtUtil.tokenValido(token);
            System.out.println("TOKEN É VALIDO? " + eValido); // PRINT 2

            if(eValido){
                String username = jwtUtil.getUsername(token);
                UserDetails user = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("AUTENTICAÇÃO SETADA NO CONTEXTO!"); // PRINT 3
            }
        }
        filterChain.doFilter(request, response);
    }
}
