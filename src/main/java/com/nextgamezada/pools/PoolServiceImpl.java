package com.nextgamezada.pools;

import java.util.List;

public class PoolServiceImpl implements PoolService{

    private final PoolDAO dao;

    public PoolServiceImpl(PoolDAO poolDAO) {
        this.dao = poolDAO;
    }

    @Override
    public List<Pool> findByAll() {
        return dao.findByAll();
    }

    @Override
    public Long createPool(Pool pool) {
       return dao.createPool(pool);
    }
}
