package com.imzhizi.tdd.config;

import com.imzhizi.tdd.repository.RedisRepo;
import com.imzhizi.tdd.security.AuthenticationInterceptor;
import com.imzhizi.tdd.security.CurrentUserArgumentResolver;
import com.imzhizi.tdd.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * created by zhizi
 * on 7/26/20 17:45
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final UserService userService;

    public WebConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserArgumentResolver(userService));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(userService));
    }

}