package com.imzhizi.tdd.helper;


import lombok.extern.log4j.Log4j2;
import org.springframework.util.DigestUtils;

/**
 * created by zhizi
 * on 7/8/20 18:01
 */
@Log4j2
public class MD5Helper {

    public static boolean check(String input, String salt, String fromDB) {
        String check = md5(input, salt);
        log.debug("fromDB:{}", fromDB);
        log.debug("check:{}", check);
        return fromDB.equals(check);
    }

    public static String md5(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }
}
