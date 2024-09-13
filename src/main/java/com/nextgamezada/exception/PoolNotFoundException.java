package com.nextgamezada.exception;

public class PoolNotFoundException extends RuntimeException {

    public PoolNotFoundException(Long id) {
        super(String.format("Pool with id %d not found", id));
    }

    public PoolNotFoundException(String message) {super(message);}
}
