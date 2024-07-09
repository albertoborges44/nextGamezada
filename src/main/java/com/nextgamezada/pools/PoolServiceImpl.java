package com.nextgamezada.pools;

import com.nextgamezada.games.Game;
import com.nextgamezada.games.GameDAO;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class PoolServiceImpl implements PoolService{

    private final PoolDAO dao;

    private final GameDAO gameDAO;

    public PoolServiceImpl(PoolDAO poolDAO, GameDAO gameDAO) {
        this.dao = poolDAO;
        this.gameDAO = gameDAO;
    }

    @Override
    public List<Pool> findAll() {
        return dao.findAll();
    }

    @Override
    public Long createPool(Pool pool) {
       return dao.createPool(pool);
    }

    @Override
    public Long editPool(Pool pool) {
        return dao.editPool(pool);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        return dao.deletePool(ids);
    }

    public Game runPool(List<Game> gameList) {

        SecureRandom secureRandom = new SecureRandom();
        int index = secureRandom.nextInt(gameList.size());

        Game winnerGame;
        try {
            winnerGame = gameDAO.findByName(gameList.get(index).getName());
        } catch (Exception e) {
            throw new RuntimeException("Error finding the game" + e.getMessage());
        }

        return winnerGame;
    }


}
