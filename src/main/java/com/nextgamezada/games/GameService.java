package com.nextgamezada.games;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {

    Game findByName(String name);

    List<Game> findByAll();

    void createGame(String name, BigDecimal price, String genre);

    Long editGame(Game game);

    Long deletePool(List<Long> ids);
}
