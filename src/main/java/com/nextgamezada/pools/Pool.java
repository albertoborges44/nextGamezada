package com.nextgamezada.pools;

import com.nextgamezada.games.Game;

import java.util.List;

public class Pool {

    long id;

    String name;

    List<Game> games;

    int winnerGame;

    int size;

    Integer status;

    public Pool(){

    }

    public Pool(String name, int size, Integer status) {
        this.name = name;
        this.size = size;
        this.status = status;
    }

    public Pool(String name, int size, Integer status, int winnerGame) {
        this.name = name;
        this.size = size;
        this.status = status;
        this.winnerGame = winnerGame;
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

    public int getWinnerGame() {
        return winnerGame;
    }

    public void setWinnerGame(int winnerGame) {
        this.winnerGame = winnerGame;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
