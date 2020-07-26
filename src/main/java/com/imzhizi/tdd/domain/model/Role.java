package com.imzhizi.tdd.domain.model;

import lombok.Data;

/**
 * created by zhizi
 * on 7/24/20 19:45
 */
@Data
public class Role {
    public static final Role admin = new Role("管理员");

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
