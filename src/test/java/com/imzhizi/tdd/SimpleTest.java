package com.imzhizi.tdd;

import com.imzhizi.tdd.helper.MD5Helper;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * created by zhizi
 * on 7/20/20 10:26
 */
@Log4j2
public class SimpleTest {
    @Test
    public void test() {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String password = "qwer1234";
        log.info("salt: {}", salt);
        log.info(MD5Helper.md5(password, salt));
        log.info(MD5Helper.md5(password, salt));
        log.info(MD5Helper.md5(password, salt));
    }

    @Test
    public void timeConvert() {
        String time = LocalDateTime.now().plusDays(7).toString();
        log.debug(LocalDateTime.parse(time));
    }
}