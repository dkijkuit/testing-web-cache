package org.cache.testing.webcache.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemcachedCacheItem extends CacheItem implements Serializable {
    private static final long serialVersionUID = 1354L;
}
