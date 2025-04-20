package com.todolist.sea.adapter.in.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Nome deve conter apenas letras, números e espaços")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpf;

    @Valid
    @NotNull
    private EnderecoDTO endereco;

    @Valid
    @Size(min = 1)
    private List<TelefoneDTO> telefones;

    @Valid
    @Size(min = 1)
    private List<EmailDTO> emails;
}
