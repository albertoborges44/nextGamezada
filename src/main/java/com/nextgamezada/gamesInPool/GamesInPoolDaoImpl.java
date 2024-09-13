package com.nextgamezada.gamesInPool;

import com.nextgamezada.games.Game;
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
public class GamesInPoolDaoImpl implements GamesInPoolDAO {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID = "id";
    private static final String GAMENAME = "name";
    private static final String PRICE = "price";
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Game> findByPoolId(long poolId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(ID, poolId);

        String sql = """
                SELECT g.id, g.name, g.price
                from "gamesInPool" gip
                join games g on gip."gameID" = g.id 
                join pools p on gip."poolID" = p.id 
                where gip."poolID" = :id 
                """;

        return namedParameterJdbcTemplate.query(sql, parametros, new GamesInPoolMapper());
    }

    private static final class GamesInPoolMapper implements RowMapper<Game> {
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game games = new Game();
            games.setId(rs.getInt(ID));
            games.setName(rs.getString(GAMENAME));
            games.setPrice(rs.getString(PRICE));
            return games;
        }
    }


}
