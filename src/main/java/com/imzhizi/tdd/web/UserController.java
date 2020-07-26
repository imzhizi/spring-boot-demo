package com.imzhizi.tdd.web;

import com.imzhizi.tdd.domain.BusinessException;
import com.imzhizi.tdd.domain.CodeMsg;
import com.imzhizi.tdd.domain.Result;
import com.imzhizi.tdd.domain.dto.LoginDto;
import com.imzhizi.tdd.domain.vo.UserVo;
import com.imzhizi.tdd.domain.model.User;
import com.imzhizi.tdd.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * created by zhizi
 * on 7/8/20 18:56
 */
@RestController
@RequestMapping("/auth")
@Log4j2
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<UserVo> login(@RequestBody LoginDto login) throws BusinessException {
        log.info(login.toString());
        User user = userService.check(login);
        return Result.build(CodeMsg.LOGIN_SUCCESSFULLY, userService.setUser(user));
    }

    @PostMapping("/register")
    public String register(LoginDto register, Model model) {
        return "register";
    }
}
