package com.item.reservation.tool.dao;

import com.item.reservation.tool.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
@Repository
public interface UserServiceDao extends CrudRepository<User, Long> {
    @Transactional
    User findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
    @Transactional
    User findByUuid(String uuid);

}
