package com.nextgamezada.games;

import com.nextgamezada.steamApp.SteamApp;
import com.nextgamezada.steamApp.SteamAppDetails;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

public interface GameService {

    Game findByName(String name);

    List<Game> findByAll();

    void createGame(String name, BigDecimal price, String genre);

    Long editGame(Game game);

    Long deletePool(List<Long> ids);

    SteamAppDetails searchGameInSteamLibrary(String gameName) throws URISyntaxException, IOException, InterruptedException;
}
