package com.item.reservation.tool.dao;

import com.item.reservation.tool.entity.Item;
import com.item.reservation.tool.entity.RoleToUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleServiceDao extends CrudRepository<RoleToUser, Long> {
    @Transactional
    RoleToUser findByUserId(Long userId);
    @Transactional
    Iterable<RoleToUser> findAllByUserId(Long userId);
}
