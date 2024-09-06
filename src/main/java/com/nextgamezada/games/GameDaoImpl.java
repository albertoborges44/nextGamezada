package com.nextgamezada.games;

import com.nextgamezada.pools.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameDaoImpl implements GameDAO{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID = "id";

    private static final String IS_FINISHED = "isFinished";

    private static final String IS_COOP = "isCoop";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String GENRE = "genre";
    private static final String ID_LIST = "ids";
    private static final String ON_SALE = "onSale";
    private static final String MAXPLAYERS = "maxPlayers";


    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Game findByName(String name) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(NAME, name);

        String sql = "SELECT id, name, price FROM Games WHERE Games.name = :name";

        ArrayList<Game> gameList = (ArrayList<Game>) namedParameterJdbcTemplate.query(
                sql, parametros, new RowMapperResultSetExtractor<>(new GameMapper()));

        return DataAccessUtils.singleResult(gameList);

        //return namedParameterJdbcTemplate.queryForObject(sql, parametros, new GameMapper());
    }

    @Override
    public List<Game> findAll() {
        Map<String, Object> parametros = new HashMap<>();

        String sql = "SELECT id, name, price FROM Games";

        return namedParameterJdbcTemplate.query(sql, parametros, new GameMapper());
    }

    public void createGame(String name, BigDecimal price, String genre) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(NAME, name);
        parametros.put(PRICE, price);
        parametros.put(GENRE, genre);

        String sql = "INSERT INTO Games (name, price, genre)" +
                "VALUES(:name, :price, :genre)";

        namedParameterJdbcTemplate.update(sql, parametros);
    }

    public void createGameFromSteamSearch(String name, BigDecimal price, String genre, boolean isCoop, boolean onSale) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(NAME, name);
        parametros.put(PRICE, price);
        parametros.put(GENRE, genre);
        parametros.put(IS_COOP, isCoop ? 1 : 0);
        parametros.put(ON_SALE, onSale ? 1 : 0);

        String sql = "INSERT INTO Games (name, price, genre, isCoop, onSale)" +
                " VALUES(:name, :price, :genre, :isCoop, :onSale)";

        namedParameterJdbcTemplate.update(sql, parametros);
    }

    @Override
    public Long editGame(Game game) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(ID, game.getId());
        parametros.put(NAME, game.getName());
        parametros.put(IS_FINISHED, game.isFinished());
        parametros.put(IS_COOP, game.isCoop());
        parametros.put(GENRE, game.getGenre());
        parametros.put(PRICE, game.getPrice());
        parametros.put(ON_SALE, game.isOnSale());
        parametros.put(MAXPLAYERS, game.getMaxPlayers());

        String sql = "UPDATE Games " +
                "SET name = :name, " +
                "isFinished = :isFinished," +
                "isCoop = :isCoop, " +
                "genre = :genre, " +
                "price = :price, " +
                "onSale = :onSale," +
                "maxPlayers = :maxPLayers," +
                "WHERE id = :id";

        return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(ID_LIST, ids);

        String sql = "DELETE FROM Games as g " +
                "WHERE g.id IN (:ids)";

        return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    private static final class GameMapper implements RowMapper<Game> {

        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            game.setId(rs.getLong("id"));
            game.setName(rs.getString(NAME));
            game.setPrice(rs.getString(PRICE));
            return game;
        }
    }
}
