package com.imzhizi.tdd.repository;

import com.imzhizi.tdd.BaseTest;
import com.imzhizi.tdd.repository.redis.UserKey;
import com.imzhizi.tdd.domain.model.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by zhizi
 * on 6/1/20 09:33
 */
@Log4j2
public class RedisTest extends BaseTest {
    @Autowired
    private RedisRepo redisRepo;

    @Test
    public void set() {
        User user = new User();
        user.setId(1000L);
        user.setUsername("zr");
        user.setPassword("zzzzz");
        redisRepo.set(UserKey.UserById, String.valueOf(1000), user);
    }

    @Test
    public void debug() {
        User user = new User();
        user.setId(1000L);
        redisRepo.set(UserKey.AUTH, "2b0bd6cf", user);
    }

    @Test
    public void exists(){
        log.debug(redisRepo.exists(UserKey.AUTH,"7c0850ca-9490-4b82-a6a7-f503c713345a"));
        log.debug(redisRepo.exists(UserKey.AUTH,"xxxx"));
        log.debug(redisRepo.exists("name:1"));
    }

    @Test
    public void get() {
        log.debug(redisRepo.get(UserKey.UserById, String.valueOf(1000), User.class));
    }

    @Test
    public void getJson() {
        log.debug(redisRepo.get(UserKey.UserById, String.valueOf(1000)));
    }

    public void 多线程环境() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                redisRepo.push("set:1", i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                redisRepo.push("set:1", i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) ;
    }
}
