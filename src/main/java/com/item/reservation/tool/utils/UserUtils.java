package com.item.reservation.tool.utils;

import com.item.reservation.tool.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUtils {

    private static final String USER_CONTEXT = "userContext";

    public static User getUserFromSession(HttpServletRequest httpServletRequest) {
        User user = null;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession != null && httpSession.getAttribute(USER_CONTEXT) != null) {
            user = (User) httpSession.getAttribute(USER_CONTEXT);
        }
        return user;
    }
}
