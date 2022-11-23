package org.cache.testing.webcache.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.cache.testing.webcache.model.CacheItem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnBean(MemcachedClient.class)
public class MemcachedCacheService implements CacheService {
    private final MemcachedClient memcachedClient;
    @Override
    public Mono<List<CacheItem>> getAllCacheItems() {
        List<CacheItem> cacheditems = (List<CacheItem>) memcachedClient.get("cacheditems");
        try {
            Boolean aBoolean = memcachedClient.set("test1", 3421, "value1").get();
            String test1 = (String) memcachedClient.get("test1");
            log.info("Test1: {}", test1);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (cacheditems == null) cacheditems = new ArrayList<>();
        log.info("Got cache items: {}", cacheditems.size());
        return Mono.just(cacheditems);
    }

    @Override
    public Mono<Integer> insertCacheItems(List<CacheItem> cacheItems) {
        boolean result = false;
        try {
            result = memcachedClient.set("cacheditems", 29377720, cacheItems).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info("Set cache items: {}", result);
        return Mono.just(cacheItems.size());
    }

    @Override
    public Mono<Void> clearCache() {
        memcachedClient.delete("cacheditems");
        return Mono.empty();
    }
}
