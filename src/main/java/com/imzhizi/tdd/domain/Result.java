package com.imzhizi.tdd.domain;

import lombok.Data;

/**
 * created by zhizi
 * on 5/31/20 10:04
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(CodeMsg cm) {
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public Result(CodeMsg cm, T data) {
        this.code = cm.getCode();
        this.msg = cm.getMsg();
        this.data = data;
    }

    public static <T> Result<T> build(CodeMsg cm, T data) {
        return new Result<>(cm, data);
    }

    public static <T> Result<T> build(CodeMsg cm) {
        return new Result<>(cm);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CodeMsg.SUCCESS, data);
    }

    public static Result success(String msg) {
        return new Result(CodeMsg.success(msg));
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(CodeMsg.ERROR, data);
    }

    public static Result error(String msg) {
        return new Result(CodeMsg.error(msg));
    }

    public static <T> Result<T> error(BusinessException e) {
        return new Result<>(e.getCodeMsg());
    }

    public static <T> Result<T> error(Exception e) {
        return new Result<>(CodeMsg.error(e.getMessage()));
    }
}
