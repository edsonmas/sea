package com.todolist.sea.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CPF {
    private final String valor;

    public CPF(String valor) {
        if (!valor.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido: deve conter 11 dígitos numéricos.");
        }
        this.valor = valor;
    }

    public String formatado() {
        return valor.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
