package com.item.reservation.tool.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAccountExpiredException extends AuthenticationException {
    public UserAccountExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserAccountExpiredException(String msg) {
        super(msg);
    }
}
