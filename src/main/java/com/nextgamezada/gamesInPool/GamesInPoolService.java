package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;

import java.util.List;

public interface GamesInPoolService {

    List<Game> findByPoolId(int poolId);
}
