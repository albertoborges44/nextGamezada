package com.nextGamezada.games;

import org.springframework.stereotype.Service;

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
}
