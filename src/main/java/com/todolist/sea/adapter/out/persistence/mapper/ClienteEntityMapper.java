package com.todolist.sea.adapter.out.persistence.mapper;

import com.todolist.sea.adapter.out.persistence.entity.*;
import com.todolist.sea.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ClienteEntityMapper {

    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf().getValor())
                .endereco(new EnderecoEmbeddable(
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getUf(),
                        cliente.getEndereco().getComplemento()
                ))
                .build();

        // Telefones
        List<TelefoneEntity> telefones = cliente.getTelefones().stream()
                .map(t -> TelefoneEntity.builder()
                        .id(t.getId())
                        .numero(t.getNumero())
                        .tipo(TelefoneEntity.TipoTelefone.valueOf(t.getTipo().name()))
                        .cliente(clienteEntity) // SETA o cliente corretamente
                        .build())
                .collect(Collectors.toList());

        // Emails
        List<EmailEntity> emails = cliente.getEmails().stream()
                .map(e -> EmailEntity.builder()
                        .id(e.getId())
                        .valor(e.getValor())
                        .cliente(clienteEntity) // SETA o cliente corretamente
                        .build())
                .collect(Collectors.toList());

        clienteEntity.setTelefones(telefones);
        clienteEntity.setEmails(emails);

        return clienteEntity;
    }


    public static Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNome(),
                new CPF(entity.getCpf()),
                new Endereco(
                        entity.getEndereco().getCep(),
                        entity.getEndereco().getLogradouro(),
                        entity.getEndereco().getBairro(),
                        entity.getEndereco().getCidade(),
                        entity.getEndereco().getUf(),
                        entity.getEndereco().getComplemento()
                ),
                entity.getTelefones().stream()
                        .map(t -> new Telefone(t.getNumero(), Telefone.TipoTelefone.valueOf(t.getTipo().name())))
                        .collect(Collectors.toList()),
                entity.getEmails().stream()
                        .map(e -> new Email(e.getValor()))
                        .collect(Collectors.toList())
        );
    }
}
