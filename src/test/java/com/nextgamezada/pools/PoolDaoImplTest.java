package com.nextgamezada.pools;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PoolDaoImplTest {

    @Test
    void createPoolReturnsGeneratedId() {
        NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);
        PoolDaoImpl dao = new PoolDaoImpl();
        dao.setNamedParameterJdbcTemplate(jdbcTemplate);
        Pool pool = new Pool("Friday night", 4, 0);
        when(jdbcTemplate.queryForObject(anyString(), anyMap(), eq(Long.class))).thenReturn(42L);

        Long id = dao.createPool(pool);

        assertEquals(42L, id);
        verify(jdbcTemplate).queryForObject(anyString(), eq(Map.of(
                "name", "Friday night",
                "size", 4,
                "status", 0
        )), eq(Long.class));
    }

    @Test
    void setWinnerConcludesOnlyAnOpenPool() {
        NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);
        PoolDaoImpl dao = new PoolDaoImpl();
        dao.setNamedParameterJdbcTemplate(jdbcTemplate);
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int updatedPools = dao.setWinnerGameAndUpdatePoolStatus(7L, 42L);

        assertEquals(1, updatedPools);
        verify(jdbcTemplate).update(
                argThat(sql -> sql.contains("status = :status") && sql.contains("status = :openStatus")),
                eq(Map.of(
                        "id", 7L,
                        "winner_game", 42L,
                        "status", 1,
                        "openStatus", 0
                ))
        );
    }
}
