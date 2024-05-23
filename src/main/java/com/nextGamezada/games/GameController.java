package com.nextGamezada.games;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Game> createGame(@RequestBody Game newGame) {
        if (Objects.nonNull(gameService.findByName(newGame.getName()))) {
            return new ResponseEntity(new Error("Game with name " + newGame.getName() +
                    "already exists"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Game>(gameService.createGame(newGame), HttpStatus.CREATED);
    }

}
