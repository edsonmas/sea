package com.todolist.sea.application.usecase;


import com.todolist.sea.adapter.in.web.dto.EmailDTO;
import com.todolist.sea.adapter.in.web.dto.TelefoneDTO;
import com.todolist.sea.application.port.in.GerenciarClienteUseCase;
import com.todolist.sea.application.port.out.ClienteRepositoryPort;
import com.todolist.sea.domain.Cliente;
import com.todolist.sea.domain.Email;
import com.todolist.sea.domain.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GerenciarClienteService implements GerenciarClienteUseCase {

    private final ClienteRepositoryPort repository;

    @Override
    public Cliente criar(Cliente cliente) {
        return repository.salvar(cliente);
    }

    @Override
    public Cliente atualizar(UUID id, Cliente cliente) {
        Cliente existente = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar campos
        existente.setNome(cliente.getNome());
        existente.setCpf(cliente.getCpf());
        existente.setEndereco(cliente.getEndereco());

        existente.getEmails().clear();
        existente.getEmails().addAll(cliente.getEmails());

        existente.getTelefones().clear();
        existente.getTelefones().addAll(cliente.getTelefones());

        return repository.salvar(existente);
    }


    @Override
    public void excluir(UUID id) {
        repository.excluirPorId(id);
    }

    @Override
    public Cliente buscarPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public void adicionarTelefone(UUID clienteId, TelefoneDTO dto) {
        Telefone telefone = new Telefone(dto.getNumero(), Telefone.TipoTelefone.valueOf(dto.getTipo()));
        Cliente cliente = buscarPorId(clienteId);

        boolean jaExiste = cliente.getTelefones().stream()
                .anyMatch(t -> t.getNumero().equals(dto.getNumero()));

        if (jaExiste) {
            throw new IllegalArgumentException("Telefone já cadastrado para este cliente.");
        }

        repository.adicionarTelefone(clienteId, telefone);
    }

    @Override
    public void adicionarEmail(UUID clienteId, EmailDTO dto) {
        Email email = new Email(dto.getValor());
        Cliente cliente = buscarPorId(clienteId);

        boolean jaExiste = cliente.getEmails().stream()
                .anyMatch(e -> e.getValor().equalsIgnoreCase(dto.getValor()));

        if (jaExiste) {
            throw new IllegalArgumentException("E-mail já cadastrado para este cliente.");
        }

        repository.adicionarEmail(clienteId, email);
    }

}

