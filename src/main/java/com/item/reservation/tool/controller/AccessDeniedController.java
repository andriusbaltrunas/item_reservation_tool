package com.item.reservation.tool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AccessDeniedController {

    private static final String ACCESS_DENIED_VIEW = "accessDenied";
    private static final String ACCESS_DENIED_PATH = "/accessDenied";

    @RequestMapping(value = ACCESS_DENIED_PATH, method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied() {
        return ACCESS_DENIED_VIEW;
    }

}
