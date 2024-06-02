package com.nextgamezada.games;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    private final GameDAO dao;

    public GameServiceImpl(GameDAO dao) {
        this.dao = dao;
    }

    @Override
    public Game findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Game> findByAll() {
        return dao.findAll();
    }

    @Override
    public void createGame(String name, BigDecimal price, String genre) {
        dao.createGame(name, price, genre);
    }
}
