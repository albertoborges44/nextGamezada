package com.nextgamezada.pools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity createPool(@RequestBody Pool pool, @RequestParam Long id) {
        if(Objects.isNull(id)) {
            return new ResponseEntity<>(
                    new Error("Could not create pool"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "pool")
    public ResponseEntity editPool(@RequestBody Pool pool) {
        Long id = poolService.editPool(pool);
        if(Objects.isNull(id)) {
            return new ResponseEntity(
                    new Error("Could not edit pool"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "pool")
    public ResponseEntity deletePool(@RequestBody List<Long> ids) {
        Long rows = poolService.deletePool(ids);
        if(Objects.isNull(rows)) {
            return new ResponseEntity(
                    new Error("Could not delete pool(s)"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rows, HttpStatus.OK);
    }
}
