package com.todolist.sea.application;


import com.todolist.sea.application.port.out.ClienteRepositoryPort;
import com.todolist.sea.application.usecase.GerenciarClienteService;
import com.todolist.sea.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarClienteServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @InjectMocks
    private GerenciarClienteService gerenciarClienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarClienteComDadosValidos() {
        // Arrange
        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                "João da Silva",
                new CPF("12345678901"),
                new Endereco("73000000", "Rua X", "Bairro Y", "Brasília", "DF", "Apt 101"),
                List.of(new Telefone("61999999999", Telefone.TipoTelefone.CELULAR)),
                List.of(new Email("joao@email.com"))
        );

        when(clienteRepositoryPort.salvar(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente criado = gerenciarClienteService.criar(cliente);

        // Assert
        assertNotNull(criado);
        assertEquals("João da Silva", criado.getNome());
        assertEquals("12345678901", criado.getCpf().getValor());
        verify(clienteRepositoryPort, times(1)).salvar(any(Cliente.class));
    }
}
