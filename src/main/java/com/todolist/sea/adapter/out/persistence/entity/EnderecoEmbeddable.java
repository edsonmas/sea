package com.todolist.sea.adapter.out.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class EnderecoEmbeddable {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
}
