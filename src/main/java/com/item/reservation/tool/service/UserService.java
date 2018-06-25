package com.item.reservation.tool.service;

import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.exceptions.UserCreateException;
import com.item.reservation.tool.exceptions.UserEmailNotUniqueException;
import com.item.reservation.tool.form.CreateUserForm;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User createNewUser(CreateUserForm createUserForm) throws UserEmailNotUniqueException, UserCreateException;

    List<User> getAllBlockedUsers();

    List<User> getActiveUsers();

    void updateUser(User user);

    User findUserByUuid(String uuid);

    String getUniqueUserUuid();
}
