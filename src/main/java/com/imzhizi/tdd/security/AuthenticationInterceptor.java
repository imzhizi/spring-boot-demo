package com.imzhizi.tdd.security;

import com.imzhizi.tdd.domain.BusinessException;
import com.imzhizi.tdd.domain.CodeMsg;
import com.imzhizi.tdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created   by   zhizi
 * on   7/26/20   20:45
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final UserService userService;

    public AuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isProtected(request)) {
            if (!userService.checkToken(request.getHeader("user-token"))) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                throw new BusinessException(CodeMsg.WITHOUT_LOG);
            }
        }
        return true;
    }

    public boolean isProtected(HttpServletRequest request) {
        return pathMatcher.match("/api/**", request.getServletPath());
    }
}
