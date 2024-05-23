package com.nextGamezada.games;

import java.util.List;

public interface GameDAO  {

    Game findByName(String name);

    List<Game> findAll();

    Game createGame(Game game);
}
