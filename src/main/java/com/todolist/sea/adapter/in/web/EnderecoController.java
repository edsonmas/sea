package com.todolist.sea.adapter.in.web;

import com.todolist.sea.adapter.in.web.dto.EnderecoDTO;
import com.todolist.sea.application.port.out.ViaCepPort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final ViaCepPort viaCepPort;

    @Operation(summary = "Consulta um endereço a partir do CEP")
    @GetMapping("/cep/{cep}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<EnderecoDTO> buscarPorCep(@PathVariable String cep) {
        EnderecoDTO endereco = viaCepPort.consultar(cep);
        return ResponseEntity.ok(endereco);
    }
}
