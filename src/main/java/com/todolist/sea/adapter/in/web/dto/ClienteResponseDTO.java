package com.todolist.sea.adapter.in.web.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {

    private UUID id;
    private String nome;
    private String cpf;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;
    private List<EmailDTO> emails;
}
