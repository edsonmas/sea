package com.todolist.sea.application.usecase;

import com.todolist.sea.adapter.in.web.dto.EnderecoDTO;
import com.todolist.sea.application.port.out.ViaCepPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ViaCepService implements ViaCepPort {

    private static final String BASE_URL = "https://viacep.com.br/ws";

    @Override
    public EnderecoDTO consultar(String cep) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(cep, "json")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        EnderecoDTO endereco = restTemplate.getForObject(url, EnderecoDTO.class);

        if (endereco == null || endereco.getCep() == null) {
            throw new RuntimeException("CEP não encontrado ou inválido.");
        }

        return endereco;
    }
}
