package com.todolist.sea.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Endereco {

    private String cep; // sem máscara
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    public String cepFormatado() {
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }
}
