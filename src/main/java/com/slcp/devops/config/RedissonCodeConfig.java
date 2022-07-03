package com.slcp.devops.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Slcp
 * @date Created in 2021/8/2 21:54
 * @description Redisson CodeConfig
 */
@Configuration
public class RedissonCodeConfig {

    private final RedisConfigProperty redisConfigProperty;

    public RedissonCodeConfig(RedisConfigProperty redisConfigProperty) {
        this.redisConfigProperty = redisConfigProperty;
    }

    @Bean(name = "redissonClient", destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisConfigProperty.getHost() + ":" + redisConfigProperty.getPort())
                .setPassword(redisConfigProperty.getPassword())
                .setDatabase(redisConfigProperty.getDatabase())
                .setTimeout(5000)
                .setSubscriptionConnectionMinimumIdleSize(1)
                .setSubscriptionConnectionPoolSize(256)
                .setConnectTimeout(redisConfigProperty.getTimeout())
                .setConnectionPoolSize(256)
                .setConnectionMinimumIdleSize(1)
                .setRetryAttempts(3)
                .setRetryInterval(3000)
                .setIdleConnectionTimeout(30000);
        return Redisson.create(config);
    }

}