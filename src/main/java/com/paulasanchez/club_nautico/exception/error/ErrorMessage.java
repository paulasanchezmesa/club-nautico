package com.paulasanchez.club_nautico.exception.error;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private String message;


    public ErrorMessage(HttpStatus httpStatus, String message) {
        this.status=httpStatus;
        this.message=message;
    }
    public ErrorMessage(String message) {

    }
}