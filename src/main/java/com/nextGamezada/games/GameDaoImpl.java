package com.nextGamezada.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameDaoImpl implements GameDAO{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String NAME = "name";

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Game findByName(String name) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(NAME, name);

        String sql = "SELECT id, name, price FROM Games WHERE Games.name =:name";

        return namedParameterJdbcTemplate.queryForObject(sql, parametros, new GameMapper());
    }

    @Override
    public List<Game> findAll() {
        Map<String, Object> parametros = new HashMap<>();

        String sql = "SELECT id, name, price FROM Games";

        return namedParameterJdbcTemplate.query(sql, parametros, new GameMapper());
    }

    private static final class GameMapper implements RowMapper<Game> {

        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            game.setId(rs.getLong("id"));
            game.setName(rs.getString("name"));
            game.setPrice(rs.getString("price"));
            return game;
        }
    }
}
