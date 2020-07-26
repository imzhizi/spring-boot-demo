package com.imzhizi.tdd.service;

import com.imzhizi.tdd.BaseTest;
import com.imzhizi.tdd.domain.vo.UserVo;
import com.imzhizi.tdd.domain.model.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * created by zhizi
 * on 5/31/20 16:49
 */
@Log4j2
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    public void 通过ID查询() {
        log.debug(userService.getUserById(1));
        log.debug(userService.getUserById(3));
    }

    @Test
    public void transfer() {
        User user = new User();
        user.setUsername("xxxx");
        user.setHead("yyyy");
        user.setLastLoginDate(LocalDateTime.now().minusDays(2));
        log.info(user);

        log.info("result:{}", modelMapper.map(user, UserVo.class));

    }

    @Test
    public void setUser(){
        User user = new User();
        user.setId(111L);
        user.setUsername("xxxx");
        user.setHead("yyyy");
        log.info(userService.setUser(user).toString());
    }


    @Test
    @Transactional
    public void 数据插入与事务() {
        User user = new User();
        user.setUsername("tangq");
        user.setPassword("2018140639");
        User user1 = new User();
        user1.setUsername("zhour");
        user1.setPassword("2018140639");
    }
}