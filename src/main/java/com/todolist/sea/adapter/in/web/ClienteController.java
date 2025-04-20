package com.todolist.sea.adapter.in.web;


import com.todolist.sea.adapter.in.web.dto.*;
import com.todolist.sea.adapter.in.web.mapper.ClienteWebMapper;
import com.todolist.sea.application.port.in.GerenciarClienteUseCase;
import com.todolist.sea.application.port.out.ViaCepPort;
import com.todolist.sea.application.usecase.ViaCepService;
import com.todolist.sea.domain.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final GerenciarClienteUseCase gerenciarClienteUseCase;
    private final ViaCepPort viaCepPort;
    private final ClienteWebMapper mapper;

    @Operation(summary = "Lista todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ClienteResponseDTO> listarTodos() {
        return gerenciarClienteUseCase.listarTodos()
                .stream()
                .map(ClienteWebMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Busca cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ClienteResponseDTO buscar(
            @Parameter(description = "ID do cliente") @PathVariable UUID id) {
        return mapper.toResponseDTO(gerenciarClienteUseCase.buscarPorId(id));
    }

    @Operation(summary = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody @Valid ClienteRequestDTO request) {
        Cliente cliente = ClienteWebMapper.toDomain(request);
        Cliente salvo = gerenciarClienteUseCase.criar(cliente);
        ClienteResponseDTO response = ClienteWebMapper.toResponseDTO(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualiza um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ClienteResponseDTO atualizar(
            @Parameter(description = "ID do cliente") @PathVariable UUID id,
            @RequestBody @Valid ClienteRequestDTO dto) {
        return mapper.toResponseDTO(gerenciarClienteUseCase.atualizar(id, mapper.toDomain(dto)));
    }

    @Operation(summary = "Exclui um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID do cliente") @PathVariable UUID id) {
        gerenciarClienteUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/telefones")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Adiciona um telefone ao cliente")
    public ResponseEntity<Void> adicionarTelefone(
            @PathVariable UUID id,
            @RequestBody @Valid TelefoneDTO dto) {

        gerenciarClienteUseCase.adicionarTelefone(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/emails")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Adiciona um e-mail ao cliente")
    public ResponseEntity<Void> adicionarEmail(
            @PathVariable UUID id,
            @RequestBody @Valid EmailDTO dto) {

        gerenciarClienteUseCase.adicionarEmail(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}