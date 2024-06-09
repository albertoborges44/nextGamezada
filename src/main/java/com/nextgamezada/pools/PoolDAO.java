package com.nextgamezada.pools;

import java.util.List;

public interface PoolDAO {

    List<Pool> findAll();
    Long createPool(Pool pool);

    Long editPool(Pool pool);

    Long deletePool(List<Long> ids);
}
