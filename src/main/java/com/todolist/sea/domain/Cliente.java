package com.todolist.sea.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Cliente {
    private UUID id;
    private String nome;
    private CPF cpf;
    private Endereco endereco;
    private List<Telefone> telefones;
    private List<Email> emails;

    // regras de domínio aqui, como validação interna, invariantes, etc.
}
