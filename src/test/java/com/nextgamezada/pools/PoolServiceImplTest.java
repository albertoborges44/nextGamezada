package com.nextgamezada.pools;

import com.nextgamezada.games.Game;
import com.nextgamezada.games.GameDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PoolServiceImplTest {

    @Test
    void runPoolStoresWinnerAndConcludesPool() {
        PoolDAO poolDAO = mock(PoolDAO.class);
        GameDAO gameDAO = mock(GameDAO.class);
        PoolServiceImpl service = new PoolServiceImpl(poolDAO, gameDAO);
        Game game = new Game(42L, "Deep Rock Galactic", "R$ 59,99");
        when(gameDAO.findByName(game.getName())).thenReturn(game);
        when(poolDAO.setWinnerGameAndUpdatePoolStatus(7L, 42L)).thenReturn(1);

        Game winner = service.runPool(List.of(game), 7L);

        assertEquals(game, winner);
        verify(poolDAO).setWinnerGameAndUpdatePoolStatus(7L, 42L);
    }

    @Test
    void runPoolRejectsRerunWhenPoolIsNotOpen() {
        PoolDAO poolDAO = mock(PoolDAO.class);
        GameDAO gameDAO = mock(GameDAO.class);
        PoolServiceImpl service = new PoolServiceImpl(poolDAO, gameDAO);
        Game game = new Game(42L, "Deep Rock Galactic", "R$ 59,99");
        when(gameDAO.findByName(game.getName())).thenReturn(game);
        when(poolDAO.setWinnerGameAndUpdatePoolStatus(7L, 42L)).thenReturn(0);

        assertThrows(PoolNotOpenException.class, () -> service.runPool(List.of(game), 7L));
    }
}
