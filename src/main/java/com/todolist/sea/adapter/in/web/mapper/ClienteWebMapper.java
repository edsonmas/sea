package com.todolist.sea.adapter.in.web.mapper;

import com.todolist.sea.adapter.in.web.dto.*;
import com.todolist.sea.adapter.out.persistence.entity.ClienteEntity;
import com.todolist.sea.adapter.out.persistence.entity.EmailEntity;
import com.todolist.sea.adapter.out.persistence.entity.EnderecoEmbeddable;
import com.todolist.sea.adapter.out.persistence.entity.TelefoneEntity;
import com.todolist.sea.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ClienteWebMapper {
    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .id(null) // <- garante persistência nova
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
                        .id(null)
                        .numero(t.getNumero())
                        .tipo(TelefoneEntity.TipoTelefone.valueOf(t.getTipo().name()))
                        .cliente(clienteEntity) // relação bidirecional
                        .build())
                .collect(Collectors.toList());

        // Emails
        List<EmailEntity> emails = cliente.getEmails().stream()
                .map(e -> EmailEntity.builder()
                        .id(null)
                        .valor(e.getValor())
                        .cliente(clienteEntity)
                        .build())
                .collect(Collectors.toList());

        clienteEntity.setTelefones(telefones);
        clienteEntity.setEmails(emails);

        return clienteEntity;
    }

    public static Cliente toDomain(ClienteRequestDTO dto) {
        return new Cliente(
                null, // gerado aqui para fins de teste
                dto.getNome(),
                new CPF(dto.getCpf()),
                new Endereco(
                        dto.getEndereco().getCep(),
                        dto.getEndereco().getLogradouro(),
                        dto.getEndereco().getBairro(),
                        dto.getEndereco().getCidade(),
                        dto.getEndereco().getUf(),
                        dto.getEndereco().getComplemento()
                ),
                dto.getTelefones().stream()
                        .map(t -> new Telefone(t.getNumero(), Telefone.TipoTelefone.valueOf(t.getTipo())))
                        .collect(Collectors.toList()),
                dto.getEmails().stream()
                        .map(e -> new Email(e.getValor()))
                        .collect(Collectors.toList())
        );
    }

    public static ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf().formatado())
                .endereco(new EnderecoDTO(
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getUf(),
                        cliente.getEndereco().getComplemento()
                ))
                .telefones(cliente.getTelefones().stream()
                        .map(t -> new TelefoneDTO(t.getNumero(), t.getTipo().name()))
                        .collect(Collectors.toList()))
                .emails(cliente.getEmails().stream()
                        .map(e -> new EmailDTO(e.getValor()))
                        .collect(Collectors.toList()))
                .build();
    }
}
