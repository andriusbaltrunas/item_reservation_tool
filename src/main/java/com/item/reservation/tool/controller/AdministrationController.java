package com.item.reservation.tool.controller;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.entity.RoleToUser;
import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.form.ChangePassword;
import com.item.reservation.tool.service.UserRoleService;
import com.item.reservation.tool.service.UserService;
import com.item.reservation.tool.service.message.MessageService;
import com.item.reservation.tool.utils.MessageType;
import com.item.reservation.tool.utils.UserUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Controller
public class AdministrationController {

    public static final String ADMINISTRATION_VIEW = "administratorPage";
    public static final String BLOCKED_USERS_VIEW = "blockedUsersPage";
    public static final String BLOCKED_USERS_PATH = "/blockedUsers";
    public static final String ADMINISTRATE_USERS_PATH = "/admin/administrateUser";
    private static final String CHANGE_PASSWORD_VIEW = "changePassword";
    private static final String CHANGE_PASSWORD_PATH = "/changePassword";
    private static final String CHANGE_PASSWORD_FORM = "changePasswordForm";

    private final UserService userService;

    private final UserRoleService userRoleService;

    private final PasswordEncoder passwordEncoder;

    private final MessageService messageService;

    @Autowired
    public AdministrationController(final UserService userService, final UserRoleService userRoleService,
                                    final PasswordEncoder passwordEncoder, final MessageService messageService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @RequestMapping(value = ADMINISTRATE_USERS_PATH, method = RequestMethod.GET)
    public ModelAndView initAdministrationPage(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(ADMINISTRATION_VIEW);
        initUserList(modelAndView, httpServletRequest);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')")
    @RequestMapping(value = BLOCKED_USERS_PATH, method = RequestMethod.GET)
    public ModelAndView getBlockedUsers() {
        ModelAndView modelAndView = new ModelAndView(BLOCKED_USERS_VIEW);
        List<User> users = userService.getAllBlockedUsers();
        modelAndView.addObject("blockedUsers", users);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/promoteUserToModerator/{uuid}", method = RequestMethod.GET)
    public ModelAndView promoteUserToModerator(@PathVariable("uuid") String uuid, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(ADMINISTRATION_VIEW);
        String message = "com.item.reservation.tool.users.promote.success";
        MessageType messageType = MessageType.SUCCESS;
        try {
            initUserList(modelAndView, httpServletRequest);
            updateUserRole(uuid, Role.MODERATOR);
        } catch (Exception e) {
            message = "com.item.reservation.tool.users.promote.error";
            messageType = MessageType.ERROR;
            log.error("cant change user role ", e);
        }
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @RequestMapping(value = "/promoteUserToWorker/{uuid}", method = RequestMethod.GET)
    public ModelAndView promoteUserToWorker(@PathVariable("uuid") String uuid, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(ADMINISTRATION_VIEW);
        String message = "com.item.reservation.tool.users.promote.success";
        MessageType messageType = MessageType.SUCCESS;
        try {
            initUserList(modelAndView, httpServletRequest);
            updateUserRole(uuid, Role.WORKER);
        } catch (Exception e) {
            message = "com.item.reservation.tool.users.promote.error";
            messageType = MessageType.ERROR;
            log.error("cant change user role ", e);
        }
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    @RequestMapping(value = CHANGE_PASSWORD_PATH, method = RequestMethod.GET)
    public ModelAndView initChangePassword() {
        ModelAndView modelAndView = new ModelAndView(CHANGE_PASSWORD_VIEW);
        modelAndView.addObject(CHANGE_PASSWORD_FORM, new ChangePassword());
        return modelAndView;
    }

    @RequestMapping(value = CHANGE_PASSWORD_PATH, method = RequestMethod.POST)
    public ModelAndView changePassword(@Valid @ModelAttribute(CHANGE_PASSWORD_FORM) ChangePassword changePassword,
                                       BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        modelAndView.setViewName(CHANGE_PASSWORD_VIEW);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        String message = "com.item.reservation.tool.change.password.successfully";
        MessageType messageType = MessageType.SUCCESS;
        try {
            User sessionUser = UserUtils.getUserFromSession(httpServletRequest);
            if (sessionUser != null) {
                User user = userService.findUserByUuid(sessionUser.getUuid());
                user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
                userService.updateUser(user);
            }
        } catch (Exception e) {
            message = "com.item.reservation.tool.change.password.unsuccessfully";
            messageType = MessageType.ERROR;
            log.error("Can not change user password");
        }
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    private void initUserList(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        String email = StringUtils.EMPTY;
        User user = UserUtils.getUserFromSession(httpServletRequest);
        if (user != null) {
            email = user.getEmail();
        }
        final String finalEmail = email;
        List<User> users = userService.getActiveUsers().stream()
                .filter(u -> !u.getUserRoles().contains(Role.ADMIN) && !u.getEmail().equals(finalEmail))
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
    }

    private void updateUserRole(String uuid, Role role) {
        User user = userService.findUserByUuid(uuid);
        RoleToUser roleToUser = userRoleService.getRoleToUser(user.getId());
        roleToUser.setUserRoleId(role.getRoleId());
        userRoleService.updateRoleToUSer(roleToUser);
    }


}