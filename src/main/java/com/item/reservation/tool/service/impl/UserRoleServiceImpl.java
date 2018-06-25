package com.item.reservation.tool.service.impl;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.dao.UserRoleServiceDao;
import com.item.reservation.tool.entity.RoleToUser;
import com.item.reservation.tool.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleServiceDao userRoleServiceDao;

    @Override
    public List<Role> getUserRoles(Long userId) {
        List<Role> roleToUsers = new ArrayList<>();
        Iterable<RoleToUser> iterable = userRoleServiceDao.findAllByUserId(userId);
        iterable.forEach(i -> roleToUsers.add(Role.getRoleById(i.getUserRoleId())));
        return roleToUsers;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        Iterable<RoleToUser> iterable = userRoleServiceDao.findAll();
        iterable.forEach(i -> roles.add(Role.getRoleById(i.getUserRoleId())));
        return roles;
    }

    @Override
    public void updateRoleToUSer(RoleToUser roleToUser) {
        userRoleServiceDao.save(roleToUser);
    }

    @Override
    public RoleToUser getRoleToUser(Long userId) {
        RoleToUser roleToUser = userRoleServiceDao.findByUserId(userId);
        return userRoleServiceDao.findByUserId(userId);
    }

    @Override
    public void insertUserRole(RoleToUser roleToUser) {
        userRoleServiceDao.save(roleToUser);
    }
}
