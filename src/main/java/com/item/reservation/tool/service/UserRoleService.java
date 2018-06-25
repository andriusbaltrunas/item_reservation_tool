package com.item.reservation.tool.service;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.entity.RoleToUser;

import java.util.List;

public interface UserRoleService {
    List<Role> getUserRoles(Long userId);

    List<Role> getAllRoles();

    void updateRoleToUSer(RoleToUser roleToUser);

    RoleToUser getRoleToUser(Long userId);

    void insertUserRole(RoleToUser roleToUser);
}
