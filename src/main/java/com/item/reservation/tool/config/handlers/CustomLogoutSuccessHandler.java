package com.item.reservation.tool.config.handlers;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger log = Logger.getLogger(CustomLogoutSuccessHandler.class);

    private static final String LOG_OUT_REDIRECT = "/login";

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("User log out successfully " + authentication.getName());
        httpServletResponse.sendRedirect(LOG_OUT_REDIRECT);
    }


}
