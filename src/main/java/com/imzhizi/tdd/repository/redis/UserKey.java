package com.imzhizi.tdd.repository.redis;

/**
 * created by zhizi
 * on 6/1/20 15:44
 */
public class UserKey extends RedisKey {
    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(String prefix, long expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static final UserKey UserById = new UserKey("user-id:");
    public static final UserKey AUTH = new UserKey("auth:", 60 * 60 * 24 * 7);
}
