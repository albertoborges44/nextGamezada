package com.nextgamezada.exception;

import org.springframework.http.HttpStatus;

public class NextGamezadaErrorBody {

    String message;

    int status;

    public NextGamezadaErrorBody(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
