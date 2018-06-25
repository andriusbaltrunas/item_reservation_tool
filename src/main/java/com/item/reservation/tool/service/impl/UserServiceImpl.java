package com.item.reservation.tool.service.impl;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.dao.UserRoleServiceDao;
import com.item.reservation.tool.entity.RoleToUser;
import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.dao.UserServiceDao;
import com.item.reservation.tool.exceptions.UserCreateException;
import com.item.reservation.tool.exceptions.UserEmailNotUniqueException;
import com.item.reservation.tool.form.CreateUserForm;
import com.item.reservation.tool.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j
@Service
public class UserServiceImpl implements UserService {

    private static final int MAX_REQUEST = 10;

    private final UserServiceDao userServiceDao;

    private final UserRoleServiceDao userRoleServiceDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserServiceDao userServiceDao, final UserRoleServiceDao userRoleServiceDao, final PasswordEncoder passwordEncoder) {
        this.userServiceDao = userServiceDao;
        this.userRoleServiceDao = userRoleServiceDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        CollectionUtils.addAll(users, userServiceDao.findAll());
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        return userServiceDao.findByEmail(email);
    }

    @Override
    public User createNewUser(CreateUserForm createUserForm) throws UserEmailNotUniqueException, UserCreateException {
        User user;
        try {
            user = getUserByEmail(createUserForm.getEmail());
            if (user != null) {
                throw new UserEmailNotUniqueException("User email " + createUserForm.getEmail() + " not unique");
            }
            user = User.transform(createUserForm);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUuid(getUniqueUserUuid());
            user = userServiceDao.save(user);
            RoleToUser roleToUser = new RoleToUser();
            roleToUser.setUserId(user.getId());
            roleToUser.setUserRoleId(Role.NOTVERIFIED.getRoleId());
            userRoleServiceDao.save(roleToUser);
        } catch (Exception e) {
            userServiceDao.deleteByEmail(createUserForm.getEmail());
            throw new UserCreateException("User do not created");
        }
        return user;
    }

    @Override
    public List<User> getAllBlockedUsers() {
        List<User> users = new ArrayList<>();
        getAllUsers().forEach(u -> {
            if (u.isBlocked() || u.isAccountExpired()) {
                User user = new User();
                BeanUtils.copyProperties(u, user);
                user.setPassword(null);
                user.setId(0L);
                users.add(u);
            }
        });
        return users;
    }

    @Override
    public List<User> getActiveUsers() {
        List<User> users = new ArrayList<>();
        getAllUsers().forEach(u -> {
            if (!u.isAccountExpired() && !u.isBlocked()) {
                User user = new User();
                BeanUtils.copyProperties(u, user);
                user.setPassword(null);
                user.setId(0L);
                users.add(user);
            }
        });
        return users;
    }

    @Override
    public void updateUser(User user) {
        userServiceDao.save(user);
    }

    @Override
    public User findUserByUuid(String uuid) {
        return userServiceDao.findByUuid(uuid);
    }

    @Override
    public String getUniqueUserUuid() {
        String uuid = StringUtils.EMPTY;
        int i = 0;
        while (i < MAX_REQUEST) {
            uuid = UUID.randomUUID().toString();
            User user = findUserByUuid(uuid);
            if (user == null) {
                break;
            }
            i++;
        }
        return uuid;
    }
}