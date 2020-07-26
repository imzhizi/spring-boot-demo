package com.imzhizi.tdd;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by zhizi
 * on 5/31/20 16:49
 */
@ActiveProfiles("test")
@SpringBootTest(classes = TddApplication.class)
@RunWith(SpringRunner.class)
@Log4j2
public class BaseTest {
    @Test
    public void test() {
        log.debug("tests run well");
    }
}
