package com.todolist.sea.adapter.in.web.exception;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
}
