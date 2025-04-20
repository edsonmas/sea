package com.todolist.sea.application.port.out;

import com.todolist.sea.adapter.in.web.dto.EnderecoDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaCepPort {
    EnderecoDTO consultar(String cep);
}
