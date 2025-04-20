package com.todolist.sea.adapter.in.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTO {

    @Pattern(regexp = "\\d{10,11}", message = "Número de telefone deve conter 10 ou 11 dígitos")
    private String numero;

    @NotNull
    private String tipo; // RESIDENCIAL, COMERCIAL, CELULAR
}
