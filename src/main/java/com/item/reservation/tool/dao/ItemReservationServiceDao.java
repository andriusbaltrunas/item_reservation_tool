package com.item.reservation.tool.dao;

import com.item.reservation.tool.entity.ItemReservation;
import org.springframework.data.repository.CrudRepository;

public interface ItemReservationServiceDao extends CrudRepository<ItemReservation, Long> {
}
