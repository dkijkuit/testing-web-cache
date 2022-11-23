package org.cache.testing.webcache.config;

import net.spy.memcached.MemcachedClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@ConditionalOnClass(MemcachedClient.class)
@ConditionalOnProperty(name = "application.cache.implementation", havingValue = "memcached")
public class MemcachedCacheConfig {
    @Bean
    MemcachedClient memcachedClient() throws IOException {
        return new MemcachedClient(new InetSocketAddress("192.168.1.63", 11211));
    }
}
