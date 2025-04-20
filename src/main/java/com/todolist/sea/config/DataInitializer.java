package com.todolist.sea.config;

import com.todolist.sea.adapter.out.persistence.entity.UsuarioEntity;
import com.todolist.sea.adapter.out.persistence.entity.UsuarioEntity.Role;
import com.todolist.sea.adapter.out.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByLogin("admin").isEmpty()) {
            usuarioRepository.save(
                UsuarioEntity.builder()
                    .login("admin")
                    .senha(passwordEncoder.encode("123qwe!@#"))
                    .role(Role.ADMIN)
                    .build()
            );
        }

        if (usuarioRepository.findByLogin("usuario").isEmpty()) {
            usuarioRepository.save(
                UsuarioEntity.builder()
                    .login("usuario")
                    .senha(passwordEncoder.encode("123qwe123"))
                    .role(Role.USER)
                    .build()
            );
        }
    }
}
