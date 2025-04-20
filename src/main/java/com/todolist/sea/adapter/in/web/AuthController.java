package com.todolist.sea.adapter.in.web;

import com.todolist.sea.adapter.in.web.dto.AuthRequestDTO;
import com.todolist.sea.adapter.in.web.dto.AuthResponseDTO;
import com.todolist.sea.adapter.out.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto) {
        AuthResponseDTO token = authService.authenticate(dto);
        return ResponseEntity.ok(token);
    }
}
