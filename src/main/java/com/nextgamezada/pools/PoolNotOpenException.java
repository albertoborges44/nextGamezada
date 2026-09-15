package com.nextgamezada.pools;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PoolNotOpenException extends RuntimeException {

    public PoolNotOpenException(long poolId) {
        super(String.format("Pool with id %d is not open", poolId));
    }
}
