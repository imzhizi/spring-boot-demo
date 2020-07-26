package com.imzhizi.tdd.domain.model;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * created by zhizi
 * on 5/31/20 16:40
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String head;
    private Integer loginCount;
    private LocalDateTime createDate;
    private LocalDateTime lastLoginDate;

}