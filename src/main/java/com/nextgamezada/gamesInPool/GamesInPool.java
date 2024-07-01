package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;

import java.util.List;

public class GamesInPool {

    private int id;

    private List<Game> gameList;

    private int poolId;

    public GamesInPool() {
    }

    public GamesInPool(int id, List<Game> gameList, int poolId) {
        this.id = id;
        this.gameList = gameList;
        this.poolId = poolId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
