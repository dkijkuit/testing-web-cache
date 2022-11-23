package org.cache.testing.webcache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnProperty(name = "application.cache.implementation", havingValue = "redis")
public class RedisCacheConfig {
    @Bean
    public RedissonClient redisCacheClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://192.168.1.63:6384")
                .addNodeAddress("redis://192.168.1.63:6380")
                .addNodeAddress("redis://192.168.1.63:6381")
                .addNodeAddress("redis://192.168.1.63:6382")
                .addNodeAddress("redis://192.168.1.63:6383")
                .addNodeAddress("redis://192.168.1.63:6384").setPassword("master123");
        return Redisson.create(config);
    }
}
