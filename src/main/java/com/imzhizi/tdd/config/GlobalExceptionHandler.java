package com.imzhizi.tdd.config;

import com.imzhizi.tdd.domain.BusinessException;
import com.imzhizi.tdd.domain.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by zhizi
 * on 7/24/20 15:14
 */
@Log4j2
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<Exception> exceptionHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            return Result.error((BusinessException) e);
        } else {
            return Result.error(e);
        }
    }
}
