package com.imzhizi.tdd.repository.redis;

import lombok.Data;

/**
 * created by zhizi
 * on 6/1/20 15:40
 */
@Data
public abstract class RedisKey {
    private String prefix;

    // 0 代表永不过期
    private long expireSeconds;
    private byte database;

    public RedisKey(String prefix) {
        this.prefix = prefix;
        this.expireSeconds = 0;
        this.database = 0;
    }

    public RedisKey(String prefix, long expireSeconds) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
        this.database = 0;
    }

    public RedisKey(String prefix, long expireSeconds, byte database) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
        this.database = database;
    }
}
