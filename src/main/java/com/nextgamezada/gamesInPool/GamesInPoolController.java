package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gamesInPool")
public class GamesInPoolController {

    private final GamesInPoolService gamesInPoolService;

    public GamesInPoolController(GamesInPoolService gamesInPoolService) {
        this.gamesInPoolService = gamesInPoolService;
    }

    @GetMapping("/games/{poolId}")
    @ResponseBody
    public ResponseEntity<List<Game>> getAllGamesByPoolId(@PathVariable("poolId") int poolId) {
        try {
            List<Game> gameList = gamesInPoolService.findByPoolId(poolId);
            if (Objects.isNull(gameList)) {
                return new ResponseEntity(new Error("Sorry, no such pool exists!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(gameList, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/games/")
    @ResponseBody
    public ResponseEntity<?> addGameToPool(@RequestParam long poolId, @RequestParam long gameId) {
        gamesInPoolService.addGameToPool(poolId, gameId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
