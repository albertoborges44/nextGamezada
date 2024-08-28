package com.nextgamezada.games;

import com.google.gson.Gson;
import com.nextgamezada.steamApp.SteamApp;
import com.nextgamezada.utils.RestApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    private final GameDAO dao;

    private final RestApiClient restApiClient;

    public GameServiceImpl(GameDAO dao, RestApiClient restApiClient) {
        this.dao = dao;
        this.restApiClient = restApiClient;
    }

    @Override
    public Game findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Game> findByAll() {
        return dao.findAll();
    }

    @Override
    public void createGame(String name, BigDecimal price, String genre) {
        dao.createGame(name, price, genre);
    }

    public Long editGame(Game game) {
        return dao.editGame(game);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        return dao.deletePool(ids);
    }

    @Override
    public SteamApp searchGameInSteamLibrary(String gameName) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> allGames = restApiClient.getAllSteamGames();

        int indexOfDesiredGame = allGames.body().indexOf(gameName);
        String stringJsonOfDesiredGame = trimDesiredGameJson(allGames.body(), indexOfDesiredGame);

        Gson gson = new Gson();
        SteamApp steamApp = gson.fromJson(stringJsonOfDesiredGame, SteamApp.class);

        return steamApp;

        //TODO: find the game, trim and parse from JSON to object

    }

    private String trimDesiredGameJson(String allGameFromSteamJson, int indexOfDesiredGame) {

        int indexOfStartingBracket = allGameFromSteamJson.lastIndexOf("{", indexOfDesiredGame);
        int indexOfClosingBracket = allGameFromSteamJson.indexOf("}", indexOfDesiredGame);

        return allGameFromSteamJson.substring(indexOfStartingBracket, indexOfClosingBracket + 1);

    }
}
