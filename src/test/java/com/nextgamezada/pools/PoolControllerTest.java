package com.nextgamezada.pools;

import com.nextgamezada.gamesInPool.GamesInPoolService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PoolControllerTest {

    @Test
    void createPoolPersistsPoolAndReturnsGeneratedId() {
        PoolService poolService = mock(PoolService.class);
        GamesInPoolService gamesInPoolService = mock(GamesInPoolService.class);
        PoolController controller = new PoolController(poolService, gamesInPoolService);
        Pool pool = new Pool("Friday night", 4, 0);
        when(poolService.createPool(pool)).thenReturn(42L);

        ResponseEntity<?> response = controller.createPool(pool);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(42L, response.getBody());
        verify(poolService).createPool(pool);
    }
}
