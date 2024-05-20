package com.nextGamezada.games;

import java.util.List;

public interface GameService {

    Game findByName(String name);

    List<Game> findByAll();
}
