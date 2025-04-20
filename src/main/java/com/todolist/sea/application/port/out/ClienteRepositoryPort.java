package com.todolist.sea.application.port.out;

import com.todolist.sea.domain.Cliente;
import com.todolist.sea.domain.Email;
import com.todolist.sea.domain.Telefone;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(UUID id);
    List<Cliente> listarTodos();
    void excluirPorId(UUID id);
    void adicionarTelefone(UUID clienteId, Telefone telefone);
    void adicionarEmail(UUID clienteId, Email email);

}
