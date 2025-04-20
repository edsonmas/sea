package com.todolist.sea.adapter.in.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {

    @Email
    @NotBlank
    private String valor;
}
