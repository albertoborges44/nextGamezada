package com.nextgamezada.pools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class PoolDaoImpl implements PoolDAO{

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SIZE = "size";
    private static final String STATUS = "status";
    private static final String WINNER_GAME = "winner_game";
    private static final String ID_LIST = "ids";
    private static final String POOL_ID = "pool_id";

    private static final String POOL_NAME = "pool_name";
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Pool> findAll() {
        HashMap<String, Object> parametros = new HashMap<>();
        String sql = "SELECT id as pool_id, name as pool_name, size, status FROM Pools";

        return namedParameterJdbcTemplate.query(sql, parametros, new PoolMapper());
    }

    public Long createPool(Pool pool) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(NAME, pool.getName());
        parametros.put(SIZE, pool.getSize());
        parametros.put(STATUS, pool.getStatus());


        String sql = "INSERT INTO Pools (name, size, status)" +
                "VALUES(:name, :size, :status)";

       return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    @Override
    public Long editPool(Pool pool) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(ID, pool.getId());
        parametros.put(NAME, pool.getName());
        parametros.put(SIZE, pool.getSize());
        parametros.put(STATUS, pool.getStatus());
        parametros.put(WINNER_GAME, pool.getWinnerGame());

        String sql = "UPDATE Pools " +
                "SET name = :name, " +
                "size = :size, " +
                "status = :status, " +
                "winner_game = :winner_game " +
                "WHERE id = :id";

        return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(ID_LIST, ids);

        String sql = "DELETE FROM Pools as p " +
                "WHERE p.id IN (:ids)";

        return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    public static final class PoolMapper implements RowMapper<Pool> {

        @Override
        public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pool pool = new Pool();
            pool.setId(rs.getLong(POOL_ID));
            pool.setName(rs.getString(POOL_NAME));
            pool.setSize(rs.getInt(SIZE));
            pool.setStatus(rs.getInt(STATUS));

            return pool;
        }
    }

}
