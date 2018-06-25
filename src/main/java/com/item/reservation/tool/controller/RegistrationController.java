package com.item.reservation.tool.controller;

import com.item.reservation.tool.exceptions.UserCreateException;
import com.item.reservation.tool.exceptions.UserEmailNotUniqueException;
import com.item.reservation.tool.form.CreateUserForm;
import com.item.reservation.tool.service.UserService;
import com.item.reservation.tool.service.message.MessageService;
import com.item.reservation.tool.utils.MessageType;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Log4j
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private static final String REGISTRATION_VIEW = "registration";
    private static final String REGISTRATION_FORM = "registrationForm";
    private static final String SUCCESS_MESSAGE = "com.item.reservation.tool.registration.successfully";
    private static final String EMAIL_EXIST_ERROR_MESSAGE = "com.item.reservation.tool.registration.email.exist.error";
    private static final String ERROR_MESSAGE = "com.item.reservation.tool.registration.error";

    private final UserService userService;

    private final MessageService messageService;

    @Autowired
    public RegistrationController(final UserService userService, final MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initRegistration() {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW);
        modelAndView.addObject(REGISTRATION_FORM, new CreateUserForm());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registration(@Valid @ModelAttribute(REGISTRATION_FORM) CreateUserForm createUserForm, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTRATION_VIEW);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        MessageType messageType = MessageType.ERROR;
        String message = SUCCESS_MESSAGE;
        try {
            userService.createNewUser(createUserForm);
            messageType = MessageType.SUCCESS;
        } catch (UserEmailNotUniqueException e) {
            message = EMAIL_EXIST_ERROR_MESSAGE;
            log.info("user email not unique " + createUserForm.getEmail(), e);
        } catch (UserCreateException e) {
            message = ERROR_MESSAGE;
            log.error("Can not create user " + createUserForm.getEmail(), e);
        }
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }
}
