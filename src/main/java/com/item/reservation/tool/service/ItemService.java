package com.item.reservation.tool.service;

import com.item.reservation.tool.entity.Item;

import java.util.List;

public interface ItemService {

    void createNewItem(Item item);

    List<Item> getAllItems();

    Item findByUuid(String uuid);

    String getUniqueUuid();
}
