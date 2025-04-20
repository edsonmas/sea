package com.todolist.sea.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
