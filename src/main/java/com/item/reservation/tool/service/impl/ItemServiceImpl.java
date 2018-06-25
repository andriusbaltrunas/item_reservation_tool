package com.item.reservation.tool.service.impl;

import com.item.reservation.tool.dao.ItemServiceDao;
import com.item.reservation.tool.entity.Item;
import com.item.reservation.tool.service.ItemService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j
@Service
public class ItemServiceImpl implements ItemService {

    private static final int MAX_ITERATION = 10;

    private final ItemServiceDao itemServiceDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ItemServiceImpl(final ItemServiceDao itemServiceDao) {
        this.itemServiceDao = itemServiceDao;
    }

    @Override
    public void createNewItem(Item item) {
        itemServiceDao.save(item);
        entityManager.clear();
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        entityManager.clear();
        Iterable<Item> iterable = itemServiceDao.findAll();
        iterable.forEach(i -> {
            Item item = new Item();
            BeanUtils.copyProperties(i, item);
            item.setId(0L);
            items.add(item);
        });
        return items;
    }

    @Override
    public Item findByUuid(String uuid) {
        return itemServiceDao.findByUuid(uuid);
    }

    @Override
    public String getUniqueUuid() {
        String uuid = StringUtils.EMPTY;
        int i = 0;
        while (i < MAX_ITERATION) {
            uuid = UUID.randomUUID().toString();
            Item item = findByUuid(uuid);
            if (item == null) {
                break;
            }
            i++;
        }
        return uuid;
    }
}
