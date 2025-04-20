package com.todolist.sea.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Clientes")
                .version("1.0")
                .description("Documentação da API para o desafio backend - arquitetura hexagonal"));
    }
}
