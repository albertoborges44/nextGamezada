package com.nextgamezada.pools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/pools")
public class PoolController {

    private final PoolService poolService;

    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    public ResponseEntity createPool(@RequestBody Pool pool) {
        Long id = poolService.createPool(pool);
        if(Objects.isNull(id)) {
            return new ResponseEntity<>(
                    new Error("Could not create pool"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
}
