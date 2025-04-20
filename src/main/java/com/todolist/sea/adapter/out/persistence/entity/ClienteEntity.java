package com.todolist.sea.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String cpf;

    @Embedded
    private EnderecoEmbeddable endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneEntity> telefones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailEntity> emails;

}
