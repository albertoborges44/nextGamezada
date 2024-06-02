package com.nextgamezada.pools;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PoolService {

    List<Pool> findByAll();
    Long createPool(Pool pool);
}
