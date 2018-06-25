package com.item.reservation.tool.config.handlers;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = Logger.getLogger(CustomLogoutSuccessHandler.class);

    private static final String ACCESS_DENIED_VIEW = "/accessDenied";

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.warn("User do not have access to page >>> " + httpServletRequest.getRequestURI() + " " + e);
        httpServletResponse.sendRedirect(ACCESS_DENIED_VIEW);
    }
}
