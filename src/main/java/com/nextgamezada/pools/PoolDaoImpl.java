package com.nextgamezada.pools;

import com.nextgamezada.enums.StatusEnum;
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
        parametros.put("name", pool.getName());
        parametros.put("size", pool.getSize());
        parametros.put("status", pool.getStatus());


        String sql = "INSERT INTO Pools (name, size, status)" +
                "VALUES(:name, :size, :status)";

       return (long) namedParameterJdbcTemplate.update(sql, parametros);
    }

    public static final class PoolMapper implements RowMapper<Pool> {

        @Override
        public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pool pool = new Pool();
            pool.setId(rs.getLong("pool_id"));
            pool.setName(rs.getString("pool_name"));
            pool.setSize(rs.getInt("size"));
            pool.setStatus(rs.getInt("status"));

            return pool;
        }
    }

}
