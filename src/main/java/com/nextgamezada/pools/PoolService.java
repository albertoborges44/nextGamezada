package com.nextgamezada.pools;

import org.springframework.stereotype.Service;

import java.util.List;

public interface PoolService {

    List<Pool> findAll();
    Long createPool(Pool pool);
}
