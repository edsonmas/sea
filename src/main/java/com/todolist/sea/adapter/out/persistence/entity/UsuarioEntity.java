package com.todolist.sea.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, USER
    }
}
