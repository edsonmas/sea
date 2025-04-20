package com.todolist.sea.application.port.in;

import com.todolist.sea.adapter.in.web.dto.EmailDTO;
import com.todolist.sea.adapter.in.web.dto.TelefoneDTO;
import com.todolist.sea.domain.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface GerenciarClienteUseCase {
    Cliente criar(Cliente cliente);
    Cliente atualizar(UUID id, Cliente cliente);
    void excluir(UUID id);
    Cliente buscarPorId(UUID id);
    List<Cliente> listarTodos();

    void adicionarEmail(UUID id, EmailDTO dto);

    void adicionarTelefone(UUID id, TelefoneDTO dto);
}
