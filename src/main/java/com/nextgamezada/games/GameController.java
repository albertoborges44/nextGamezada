package com.nextgamezada.games;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gamesList = gameService.findByAll();
        if (gamesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }

    @GetMapping(value = "/game")
    public ResponseEntity<Game> getByName(@PathVariable("name") String name) {
        Game game = gameService.findByName(name);
        if (Objects.isNull(game)) {
            return new ResponseEntity(new Error("Game with name" + name +
                    " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @PostMapping(value = "/game")
    public void createGame(@RequestParam String name, @RequestParam BigDecimal price, @RequestParam String genre) {
        gameService.createGame(name, price, genre);
    }

    @PutMapping(value = "game")
    public ResponseEntity editGame(@RequestBody Game game) {
        Long row = gameService.editGame(game);
        if(Objects.isNull(row)) {
            return new ResponseEntity(
                    new Error("Could not edit game"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(row, HttpStatus.OK);
    }

    @DeleteMapping(value = "game")
    public ResponseEntity deleteGame(@RequestBody List<Long> ids) {
        Long rows = gameService.deletePool(ids);
        if(Objects.isNull(rows)) {
            return new ResponseEntity(
                    new Error("Could not delete pool(s)"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rows, HttpStatus.OK);
    }

}
