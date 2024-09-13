package com.nextgamezada.pools;

import com.nextgamezada.exception.PoolNotFoundException;
import com.nextgamezada.games.Game;
import com.nextgamezada.gamesInPool.GamesInPoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pools")
public class PoolController {

    private final PoolService poolService;

    private final GamesInPoolService gamesInPoolService;

    public PoolController(PoolService poolService, GamesInPoolService gamesInPoolService) {
        this.poolService = poolService;
        this.gamesInPoolService = gamesInPoolService;
    }

    @GetMapping(value = "/pools")
    public ResponseEntity<List<Pool>> getAllPools() {
        List<Pool> poolsList = poolService.findAll();
        if(poolsList.isEmpty()) {
            throw new PoolNotFoundException("There are no pools registered.");
        }
           return new ResponseEntity<>(poolsList, HttpStatus.OK);
    }

    @PostMapping(value = "/pool")
    public ResponseEntity createPool(@RequestBody Pool pool, @RequestParam Long id) {
        if(Objects.isNull(id)) {
            return new ResponseEntity<>(
                    new Error("Could not create pool"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "pool")
    public ResponseEntity editPool(@RequestBody Pool pool) {
        Long id = poolService.editPool(pool);
        if(id == 0) {
            throw new PoolNotFoundException(pool.getId());
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "pool")
    public ResponseEntity deletePool(@RequestBody List<Long> ids) {
        Long rows = poolService.deletePool(ids);
        if(Objects.isNull(rows)) {
            return new ResponseEntity(
                    new Error("Could not delete pool(s)"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }
    @PostMapping(value = "/runPool")
    public ResponseEntity runPool(@RequestParam long poolId) {
        List<Game> gamesInPoolList = gamesInPoolService.findByPoolId(poolId);

        if(gamesInPoolList.isEmpty()) {
            throw new PoolNotFoundException(poolId);
        }
        Game winnerGame = poolService.runPool(gamesInPoolList, poolId);
        return new ResponseEntity<>(winnerGame, HttpStatus.OK);
    }
}
