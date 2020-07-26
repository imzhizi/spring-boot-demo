package com.imzhizi.tdd.repository.redis;

/**
 * created by zhizi
 * on 6/1/20 15:51
 */
public class OrderKey extends RedisKey{
    public OrderKey(String prefix) {
        super(prefix);
    }

    public OrderKey(String prefix, long expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static OrderKey OrderByUser = new OrderKey("order-user:");
    public static OrderKey OrderById = new OrderKey("order-id:");
}
