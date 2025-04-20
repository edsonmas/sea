package com.todolist.sea.adapter.out.persistence;


import com.todolist.sea.adapter.out.persistence.entity.ClienteEntity;
import com.todolist.sea.adapter.out.persistence.mapper.ClienteEntityMapper;
import com.todolist.sea.adapter.out.persistence.repository.SpringDataClienteRepository;
import com.todolist.sea.application.port.out.ClienteRepositoryPort;
import com.todolist.sea.domain.Cliente;
import com.todolist.sea.domain.Email;
import com.todolist.sea.domain.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final SpringDataClienteRepository repository;
    private final ClienteEntityMapper mapper;

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente); // use o mapper ajustado acima
        ClienteEntity salvo = repository.save(entity);
        return mapper.toDomain(salvo); // converte de volta para o domínio
    }


    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return repository.findById(id).map(ClienteEntityMapper::toDomain);
    }

    @Override
    public List<Cliente> listarTodos() {
        return repository.findAll().stream()
                .map(ClienteEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void excluirPorId(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void adicionarTelefone(UUID clienteId, Telefone telefone) {
        Cliente cliente = buscarPorId(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.getTelefones().add(telefone);
        salvar(cliente); // garante persistência
    }

    @Override
    public void adicionarEmail(UUID clienteId, Email email) {
        Cliente cliente = buscarPorId(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.getEmails().add(email);
        salvar(cliente);
    }

}
