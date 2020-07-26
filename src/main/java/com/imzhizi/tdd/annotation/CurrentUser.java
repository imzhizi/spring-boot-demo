package com.imzhizi.tdd.annotation;

import java.lang.annotation.*;

/**
 * created by zhizi
 * on 7/26/20 09:29
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    boolean required() default true;
}
