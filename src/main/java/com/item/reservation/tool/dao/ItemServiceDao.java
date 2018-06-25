package com.item.reservation.tool.dao;

import com.item.reservation.tool.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemServiceDao extends CrudRepository<Item, Long> {
    @Transactional
    Item findByUuid(String uuid);
}
