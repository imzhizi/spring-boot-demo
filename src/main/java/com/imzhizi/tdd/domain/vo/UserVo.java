package com.imzhizi.tdd.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhizi
 * on 7/8/20 19:01
 */
@Data
public class UserVo {
    private Long id;
    private String username;
    private String name;
    private String head;
    private String token;
    private Integer loginCount;
    private LocalDateTime lastLoginDate;
}
