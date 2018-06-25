package com.item.reservation.tool.bean;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1), MODERATOR(2), WORKER(3), VERIFIED(4), NOTVERIFIED(5), DEFAULT(0);

    private int roleId;

    Role(int roleId){
        this.roleId = roleId;
    }

    public static Role getRoleById(int userRoleId){
        Role role = DEFAULT;
        for(Role r : Role.values()){
            if(userRoleId == r.roleId){
                role = r;
            }
        }
        return role;
    }
}
