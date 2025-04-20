package com.todolist.sea.adapter.in.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String login;
    private String senha;
}
