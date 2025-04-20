package com.todolist.sea.domain;

import lombok.*;

@Getter
@EqualsAndHashCode
public class Email {

    private Integer id;
    private final String valor;

    public Email(String valor) {
        if (!valor.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.valor = valor;
    }
}
