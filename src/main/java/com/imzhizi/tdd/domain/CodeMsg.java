package com.imzhizi.tdd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by zhizi
 * on 5/31/20 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
    public static final CodeMsg SUCCESS = success("请求成功");
    public static final CodeMsg ERROR = error("请求错误");
    public static final CodeMsg WRONG_FORMAT = build(5001, "请求信息不全");


    public static final CodeMsg USER_INFO_ERROR = build(4000, "用户信息异常");
    public static final CodeMsg LOGIN_SUCCESSFULLY = build(5100, "登录成功");
    public static final CodeMsg UN_EXISTS = build(5101, "用户不存在");
    public static final CodeMsg WRONG_PASSWORD = build(5102, "密码错误");
    public static CodeMsg WITHOUT_LOG = error("尚未登录或登录信息已失效");


    private Integer code;
    private String msg;


    public static CodeMsg build(int code, String message) {
        return new CodeMsg(code, message);
    }

    public static CodeMsg error(String message) {
        return new CodeMsg(5000, message);
    }

    public static CodeMsg success(String message) {
        return new CodeMsg(1000, message);
    }
}
