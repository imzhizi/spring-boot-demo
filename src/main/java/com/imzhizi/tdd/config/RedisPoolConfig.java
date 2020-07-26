package com.imzhizi.tdd.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * created by zhizi
 * on 6/1/20 09:03
 */
@Configuration
public class RedisPoolConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private Long maxWait;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;


    @Bean
    public JedisPool jedisPool() {
        GenericObjectPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }
}
