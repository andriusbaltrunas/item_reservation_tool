package com.item.reservation.tool.config.handlers;

import com.item.reservation.tool.exceptions.UserAccountExpiredException;
import com.item.reservation.tool.exceptions.UserDontExistException;
import com.item.reservation.tool.exceptions.UserIsBlockedException;
import com.item.reservation.tool.service.message.MessageService;
import com.item.reservation.tool.utils.MessageType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = Logger.getLogger(CustomAuthenticationFailureHandler.class);

    private static final String DEFAULT_FAIL_URL = "/login";

    @Autowired
    private MessageService messageService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String message = messageService.message("com.item.reservation.tool.user.incorrect.credentials.error");

        if (e.getCause() instanceof UserDontExistException) {
            log.info("User do not exist or wrong credentials ");
            message = messageService.message("com.item.reservation.tool.user.not.exist.error");
        }
        if(e.getCause() instanceof UserIsBlockedException){
            log.info("User is blocked");
            message = messageService.message("com.item.reservation.tool.user.blocked.error");
        }
        if (e.getCause() instanceof UserAccountExpiredException) {
            log.info("User account expired");
            message = messageService.message("com.item.reservation.tool.user.account.expired.error");
        }
        httpServletRequest.setAttribute(MessageType.ERROR.getType(), message);
        httpServletRequest.getRequestDispatcher(DEFAULT_FAIL_URL).forward(httpServletRequest, httpServletResponse);
    }
}