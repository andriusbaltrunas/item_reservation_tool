package com.item.reservation.tool.service.impl;

import com.item.reservation.tool.dao.ItemReservationServiceDao;
import com.item.reservation.tool.entity.ItemReservation;
import com.item.reservation.tool.service.ItemReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ItemReservationServiceImpl implements ItemReservationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemReservationServiceDao itemReservationServiceDao;

    @Override
    public void reserveItem(ItemReservation itemReservation) {
        itemReservationServiceDao.save(itemReservation);
        entityManager.clear();
    }

    @Override
    public void deleteReservationById(Long id) {
        itemReservationServiceDao.deleteById(id);
        entityManager.clear();
    }

    @Override
    public void deleteReservation(ItemReservation itemReservation) {
        itemReservationServiceDao.delete(itemReservation);
        entityManager.clear();
    }
}
