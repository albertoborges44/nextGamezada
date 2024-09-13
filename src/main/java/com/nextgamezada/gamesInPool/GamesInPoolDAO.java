package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;

import java.util.List;

public interface GamesInPoolDAO {

    List<Game> findByPoolId(long poolId);
}
