package com.imzhizi.tdd.repository;

import com.alibaba.fastjson.JSON;
import com.imzhizi.tdd.repository.redis.RedisKey;
import com.imzhizi.tdd.repository.redis.UserKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * created by zhizi
 * on 5/31/20 19:26
 */
@Log4j2
@Repository
public class RedisRepo {
    private final JedisPool jedisPool;

    public RedisRepo(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean exists(RedisKey prefix, String key) {
        return exists(prefix.getPrefix() + key);
    }

    public boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try (Jedis jedis = jedisPool.getResource()) {
            return toBean(jedis.get(key), clazz);
        }
    }

    public <T> String get(String key) {
        return get(key, String.class);
    }

    public String get(RedisKey prefix, String key) {
        return get(prefix.getPrefix() + key, String.class);
    }

    public <T> T get(RedisKey prefix, String key, Class<T> clazz) {
        return get(prefix.getPrefix() + key, clazz);
    }

    public <T> void set(String key, T val, long expireSeconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            log.debug(jedis.psetex(key, expireSeconds * 1000, toString(val)));
        }

    }

    public <T> void set(String key, T val) {
        try (Jedis jedis = jedisPool.getResource()) {
            log.debug(jedis.set(key, toString(val)));
        }
    }

    public <T> void set(RedisKey prefix, String key, T val) {
        if (prefix.getExpireSeconds() > 0) {
            set(prefix.getPrefix() + key, val, prefix.getExpireSeconds());
        } else {
            set(prefix.getPrefix() + key, val);
        }
    }

    public void push(String key, Object obj) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.lpush(key, obj.toString());
        }
    }

    private static <T> T toBean(String string, Class<T> clazz) {
        if (string == null || string.isEmpty()) {
            return null;
        } else if (String.class.equals(clazz)) {
            return (T) string;
        } else if (Integer.class.equals(clazz)) {
            return (T) Integer.valueOf(string);
        } else if (Long.class.equals(clazz)) {
            return (T) Long.valueOf(string);
        } else {
            return JSON.parseObject(string, clazz);
        }
    }

    private static <T> String toString(T val) {
        if (Integer.class.equals(val.getClass()) || Long.class.equals(val.getClass())) {
            return String.valueOf(val);
        } else {
            return JSON.toJSONString(val);
        }
    }
}
