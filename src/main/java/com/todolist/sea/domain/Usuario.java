package com.todolist.sea.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private UUID id;
    private String login;
    private String senha;
    private Role role;

    public enum Role {
        ADMIN, USER
    }
}
