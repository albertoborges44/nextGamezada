package com.nextgamezada.pools;

import com.nextgamezada.games.Game;

import java.util.List;

public class Pool {

    long id;

    String name;

    List<Game> games;

    Game winnerGame;

    public Pool(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Game getWinnerGame() {
        return winnerGame;
    }

    public void setWinnerGame(Game winnerGame) {
        this.winnerGame = winnerGame;
    }
}
