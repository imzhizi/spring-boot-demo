package com.imzhizi.tdd.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhizi
 * on 7/8/20 19:03
 */
@Data
public class RegisterDto {
    private String username;
    private String password;
}
