package com.imzhizi.tdd.security;

import com.imzhizi.tdd.annotation.CurrentUser;
import com.imzhizi.tdd.repository.RedisRepo;
import com.imzhizi.tdd.repository.redis.KeyUser;
import com.imzhizi.tdd.repository.redis.UserKey;
import com.imzhizi.tdd.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * created by zhizi
 * on 7/26/20 09:33
 */
@Log4j2
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    public CurrentUserArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(org.springframework.core.MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(org.springframework.core.MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        CurrentUser annotation = methodParameter.getParameterAnnotation(CurrentUser.class);
        if (annotation == null) {
            return null;
        } else {
            String token = nativeWebRequest.getHeader("user-token");
            if (token == null) {
                throw new IllegalArgumentException("auth info lost");
            }
            KeyUser user = userService.getUserByToken(token);
            if (user == null && annotation.required()) {
                throw new IllegalArgumentException("auth failed");
            }
            return user;
        }
    }
}