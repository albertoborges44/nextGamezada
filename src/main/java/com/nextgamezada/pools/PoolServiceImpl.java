package com.nextgamezada.pools;

import com.nextgamezada.games.Game;
import com.nextgamezada.games.GameDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    @Transactional
    public Game runPool(List<Game> gameList, long poolId) {

        int index = ThreadLocalRandom.current().nextInt(gameList.size());

        Game winnerGame = gameDAO.findByName(gameList.get(index).getName());
        int updatedPools = dao.setWinnerGameAndUpdatePoolStatus(poolId, winnerGame.getId());

        if (updatedPools == 0) {
            throw new PoolNotOpenException(poolId);
        }

        return winnerGame;
    }


}
