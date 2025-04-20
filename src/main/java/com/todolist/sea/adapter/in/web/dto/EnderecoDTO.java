package com.todolist.sea.adapter.in.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {

    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    private String complemento;
}
