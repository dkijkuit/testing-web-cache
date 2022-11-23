package org.cache.testing.webcache.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cache.testing.webcache.model.CacheItem;
import org.redisson.api.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnBean(RedissonClient.class)
public class RedisCacheService implements CacheService{
    private final RedissonClient redisCacheClient;

    @Override
    public Mono<List<CacheItem>> getAllCacheItems() {
        final List<CacheItem> cacheItems = new ArrayList<>();
        RBatch clientBatch = redisCacheClient.createBatch(BatchOptions.defaults());
        Iterable<String> keysByPattern = redisCacheClient.getKeys().getKeysByPattern("cache_item*");
        List<CacheItem> cacheItemList = StreamSupport.stream(keysByPattern.spliterator(), false).map(key -> (CacheItem) redisCacheClient.getMap(key).get(key)).toList();
        log.info("Got {} items from REDIS", cacheItemList.size());
        return Mono.just(cacheItems);
    }

    @Override
    public Mono<Integer> insertCacheItems(List<CacheItem> cacheItems) {
        RBatchReactive batch = redisCacheClient.reactive().createBatch(BatchOptions.defaults());
        //cacheItems.parallelStream().forEach(cacheItem -> batch.getBucket("cache_item_" + cacheItem.getId()).set(cacheItem));
        cacheItems.parallelStream().forEach(cacheItem -> batch.getMap("cache_item_" + cacheItem.getId()).fastPut("cache_item_" + cacheItem.getId(), cacheItem).subscribe());
        Mono<BatchResult<?>> resultMono = batch.execute();
        return resultMono.map(batchResult -> batchResult.getResponses().size());
    }

    @Override
    public Mono<Void> clearCache() {
        throw new RuntimeException("Not implemented clearCache");
    }
}
