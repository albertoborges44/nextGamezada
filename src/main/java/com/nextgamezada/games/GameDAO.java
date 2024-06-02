package com.nextgamezada.games;

import java.math.BigDecimal;
import java.util.List;

public interface GameDAO  {

    Game findByName(String name);

    List<Game> findAll();

    void createGame(String name, BigDecimal price, String genre);
}
