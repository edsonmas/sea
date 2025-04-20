package com.todolist.sea.adapter.out.security;

import com.todolist.sea.adapter.in.web.dto.AuthRequestDTO;
import com.todolist.sea.adapter.in.web.dto.AuthResponseDTO;
import com.todolist.sea.adapter.out.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        var usuario = usuarioRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        String token = jwtUtil.generateToken(usuario.getLogin(), usuario.getRole().name());
        return new AuthResponseDTO(token);
    }
}
