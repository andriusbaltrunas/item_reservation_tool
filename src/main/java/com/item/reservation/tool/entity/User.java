package com.item.reservation.tool.entity;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.form.CreateUserForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAccountExpired = false;

    @Column(nullable = false)
    private boolean isBlocked;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Set<RoleToUser> rolesToUser;

    @Transient
    private Set<Role> userRoles;

    public static User transform(CreateUserForm createUserForm) {
        User user = new User();
        BeanUtils.copyProperties(createUserForm, user);
        return user;
    }

    public Set<Role> getUserRoles() {
        userRoles = new HashSet<>();
        if (rolesToUser != null) {
            rolesToUser.forEach(r -> userRoles.add(Role.getRoleById(r.getUserRoleId())));
        }
        if (userRoles.isEmpty()) {
            userRoles.add(Role.NOTVERIFIED);
        }
        return userRoles;
    }

}
