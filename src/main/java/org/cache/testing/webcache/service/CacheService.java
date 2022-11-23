package org.cache.testing.webcache.service;

import org.cache.testing.webcache.model.CacheItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CacheService {
    Mono<List<CacheItem>> getAllCacheItems();
    Mono<Integer> insertCacheItems(List<CacheItem> cacheItems);

    Mono<Void> clearCache();
}
