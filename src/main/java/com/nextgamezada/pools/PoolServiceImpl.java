package com.nextgamezada.pools;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoolServiceImpl implements PoolService{

    private final PoolDAO dao;

    public PoolServiceImpl(PoolDAO poolDAO) {
        this.dao = poolDAO;
    }

    @Override
    public List<Pool> findAll() {
        return dao.findAll();
    }

    @Override
    public Long createPool(Pool pool) {
       return dao.createPool(pool);
    }

    @Override
    public Long editPool(Pool pool) {
        return dao.editPool(pool);
    }

    @Override
    public Long deletePool(List<Long> ids) {
        return dao.deletePool(ids);
    }


}
