package com.imzhizi.tdd.web;

import com.imzhizi.tdd.annotation.CurrentUser;
import com.imzhizi.tdd.domain.Result;
import com.imzhizi.tdd.repository.redis.KeyUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by zhizi
 * on 5/31/20 09:38
 */
@RestController
@RequestMapping("/api/doctor")
@Log4j2
public class DoctorController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/info")
    public Result<KeyUser> info(@CurrentUser KeyUser currentUser) {
        log.debug(currentUser.toString());
        return Result.success(currentUser);
    }
}