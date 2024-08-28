package com.nextgamezada.games;

import com.nextgamezada.steamApp.SteamApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
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

    @GetMapping(value = "/game/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) throws URISyntaxException, IOException, InterruptedException {
        Game game = gameService.findByName(name);
        if(Objects.isNull(game)) {
            SteamApp steamApp = gameService.searchGameInSteamLibrary(name);
            return new ResponseEntity<SteamApp>(steamApp, HttpStatus.OK);
        }
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
