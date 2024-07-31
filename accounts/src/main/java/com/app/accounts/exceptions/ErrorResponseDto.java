package com.app.accounts.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private HttpStatus status;
    private Integer statusCode;
    private String errorMessage;
    private LocalDateTime localDateTime;
}
