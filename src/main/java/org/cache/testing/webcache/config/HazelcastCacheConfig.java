package org.cache.testing.webcache.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnClass(Hazelcast.class)
@ConditionalOnProperty(name = "application.cache.implementation", havingValue = "hazelcast")
public class HazelcastCacheConfig {
    @Bean
    HazelcastInstance hazelcast() {
        ClientConfig config = new ClientConfig();
        config.setNetworkConfig(new ClientNetworkConfig().setAddresses(List.of(
                "192.168.1.63:5701",
                "192.168.1.63:5702"
   /*             "192.168.1.63:5703",
                "192.168.1.63:5704",
                "192.168.1.63:5705"*/
        )));

        return HazelcastClient.newHazelcastClient(config);
    }
}
