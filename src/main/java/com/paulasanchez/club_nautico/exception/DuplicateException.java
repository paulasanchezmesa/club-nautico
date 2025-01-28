package com.paulasanchez.club_nautico.exception;

public class DuplicateException extends Exception{
    public DuplicateException() {
    }

    public DuplicateException(String message) {
        super(message);
    }
}