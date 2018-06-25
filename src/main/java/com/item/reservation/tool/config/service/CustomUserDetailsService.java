package com.item.reservation.tool.config.service;

import com.item.reservation.tool.bean.Role;
import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.exceptions.UserAccountExpiredException;
import com.item.reservation.tool.exceptions.UserDontExistException;
import com.item.reservation.tool.exceptions.UserIsBlockedException;
import com.item.reservation.tool.service.UserRoleService;
import com.item.reservation.tool.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);

    private static final String ROLE_PREFIX = "ROLE_";

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        //Exmaple if comfirmed account or need pay for license
        if (user == null) {
            throw new UserDontExistException("user do not exist " + email);
        }
        if (user.isBlocked()) {
            throw new UserIsBlockedException("User is blocked " + email);
        }
        // User account expired
        if (user.isAccountExpired()) {
            throw new UserAccountExpiredException("User account expired " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getRole(user));
    }

    private List<SimpleGrantedAuthority> getRole(User user) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Role> userRoles = user.getUserRoles();
        userRoles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + r.name())));
        if (grantedAuthorities.isEmpty()) {
            log.error("user missing roles USER role added to " + user.getEmail());
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Role.DEFAULT.name()));
        }
        return grantedAuthorities;
    }
}
