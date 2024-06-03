package com.nextgamezada.pools;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pools")
public class PoolController {

    private final PoolService poolService;

    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    @GetMapping(value = "/pools")
    public ResponseEntity<List<Pool>> getAllPools() {
        List<Pool> poolsList = poolService.findAll();
        if(poolsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
           return new ResponseEntity<>(poolsList, HttpStatus.OK);
    }

    @PostMapping(value = "/pool")
    public ResponseEntity createPool(@RequestBody Pool pool) {
        Long id = poolService.createPool(pool);
        if(Objects.isNull(id)) {
            return new ResponseEntity<>(
                    new Error("Could not create pool"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
}
