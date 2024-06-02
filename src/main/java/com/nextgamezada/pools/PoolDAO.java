package com.nextgamezada.pools;

import java.util.List;

public interface PoolDAO {

    List<Pool> findByAll();
    Long createPool(Pool pool);
}
