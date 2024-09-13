package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesInPoolServiceImpl implements GamesInPoolService{

    private final GamesInPoolDAO dao;

    public GamesInPoolServiceImpl(GamesInPoolDAO dao) {
        this.dao = dao;
    }

    public List<Game> findByPoolId(long poolId) {
        return dao.findByPoolId(poolId);
    }
}
