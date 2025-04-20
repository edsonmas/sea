package com.todolist.sea.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {

    private Integer id;
    private String numero; // sem máscara
    private TipoTelefone tipo;

    public Telefone(String numero, TipoTelefone tipoTelefone) {
        if (!numero.matches("\\d{2}\\d{4,5}\\d{4}")) {
            throw new IllegalArgumentException("Número de telefone inválido.");
        }
        this.numero = numero;
        this.tipo = tipoTelefone;
    }

    public enum TipoTelefone {
        RESIDENCIAL, COMERCIAL, CELULAR
    }

    public String formatado() {
        if (tipo == TipoTelefone.CELULAR) {
            return numero.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else {
            return numero.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        }
    }
}
