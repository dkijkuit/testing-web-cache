package org.cache.testing.webcache.service;

import com.hazelcast.cache.ICache;
import com.hazelcast.collection.IList;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Pipelining;
import com.hazelcast.jet.pipeline.BatchSource;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import org.cache.testing.webcache.annotation.PrintExecutionTime;
import org.cache.testing.webcache.model.CacheItem;
import org.cache.testing.webcache.model.HazelcastCacheItem;
import org.redisson.api.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(HazelcastInstance.class)
public class HazelcastCacheService implements CacheService {
    public static final int BATCH_SIZE = 100000;
    private final HazelcastInstance hazelcastInstance;

    @Override
    @PrintExecutionTime
    public Mono<List<CacheItem>> getAllCacheItems() {
        IList<HazelcastCacheItem> cacheitems = hazelcastInstance.getList("cacheitems");
        List<CacheItem> items = new ArrayList<>(cacheitems);
        System.out.println("Fetched " + cacheitems.size() + " items.");
        return Mono.empty();
    }

    @Override
    @PrintExecutionTime
    public Mono<Integer> insertCacheItems(List<CacheItem> cacheItems) {
        //insertUsingMap(cacheItems);
        insertUsingLists(cacheItems);

        return Mono.just(cacheItems.size());
    }

    private int insertUsingLists(List<CacheItem> cacheItems) {
        IList<CacheItem> list = hazelcastInstance.getList("cacheitems");
        if (cacheItems.size() > BATCH_SIZE) {
            int i = BATCH_SIZE;
            while(i < cacheItems.size()) {
                list.addAll(cacheItems.subList((i - BATCH_SIZE), i));
                System.out.println(">>>> Added: " + i);
                i = i + BATCH_SIZE;
            }
            list.addAll(cacheItems.subList((i - BATCH_SIZE), cacheItems.size()));
            System.out.println(">>>> Added total: " + list.size());
        } else {
            list.addAll(cacheItems);
        }

        return cacheItems.size();
    }

    private int insertUsingMap(List<CacheItem> cacheItems) {
        IMap<String, CacheItem> cacheItemIMap = hazelcastInstance.getMap("cacheitems");
        /*Map<String, CacheItem> cacheItemMap = cacheItems.stream().collect(Collectors.toMap(CacheItem::getId, cacheItem -> cacheItem));
        cacheItemIMap.putAll(cacheItemMap);*/
        Pipelining<CacheItem> pipelining = new Pipelining<>(100000);
        cacheItems.forEach(cacheItem -> {
            try {
                pipelining.add(cacheItemIMap.putAsync(cacheItem.getId(), cacheItem));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            List<CacheItem> results = pipelining.results();
            return results.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Void> clearCache() {
        hazelcastInstance.getMap("cacheitems").destroy();
        hazelcastInstance.getList("cacheitems").destroy();
        return Mono.empty();
    }
}
