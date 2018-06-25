package com.item.reservation.tool.config.handlers;

import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String WELCOME_PAGE = "welcome";
    private static final String USER_CONTEXT = "userContext";

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession != null && StringUtils.isNotBlank(authentication.getName())) {
            User user = userService.getUserByEmail(authentication.getName());
            user.setId(0L);
            user.setPassword(null);
            httpSession.setAttribute(USER_CONTEXT, user);
            log.info("User context set to session");
        }

        log.info("User login successfully");
        httpServletResponse.sendRedirect(WELCOME_PAGE);
    }
}
