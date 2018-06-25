package com.item.reservation.tool.service;

import com.item.reservation.tool.entity.ItemReservation;

public interface ItemReservationService {

    void reserveItem(ItemReservation itemReservation);

    void deleteReservationById(Long id);

    void deleteReservation(ItemReservation itemReservation);
}
