package com.imzhizi.tdd.repository.redis;

import com.imzhizi.tdd.domain.model.Role;
import lombok.Data;

import java.util.Date;

/**
 * created by zhizi
 * on 7/24/20 19:35
 */
@Data
public class KeyUser {
    private Long id;
    private String username;
    private Role role;
    private Long expireTime;

    public KeyUser(long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.expireTime = new Date().getTime() / 1000 + UserKey.AUTH.getExpireSeconds();
    }
}
