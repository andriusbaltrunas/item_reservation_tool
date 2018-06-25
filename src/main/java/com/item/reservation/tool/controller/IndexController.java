package com.item.reservation.tool.controller;

import com.item.reservation.tool.form.LoginForm;
import com.item.reservation.tool.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_VIEW = "/login";
    private static final String DEFAULT_VIEW = "/";
    private static final String LOGIN_FORM = "loginForm";

    private final MessageService messageService;

    @Autowired
    public IndexController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = {DEFAULT_VIEW, LOGIN_VIEW}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView initPage() {
        ModelAndView model = new ModelAndView(LOGIN_PAGE);
        model.addObject(LOGIN_FORM, new LoginForm());
        model.addObject("emailText", messageService.message("com.item.reservation.tool.login.email"));
        model.addObject("passwordText", messageService.message("com.item.reservation.tool.login.password"));
        return model;
    }
}
