package com.nextgamezada.pools;

import com.nextgamezada.games.Game;

import java.util.List;

public interface PoolService {

    List<Pool> findAll();
    Long createPool(Pool pool);

    Long editPool(Pool pool);

    Long deletePool(List<Long> ids);

    Game runPool(List<Game> poolList, long poolId);
}
