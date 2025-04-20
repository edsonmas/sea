package com.todolist.sea.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "telefones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public enum TipoTelefone {
        RESIDENCIAL, COMERCIAL, CELULAR
    }
}
