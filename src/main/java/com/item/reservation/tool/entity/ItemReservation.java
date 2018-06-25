package com.item.reservation.tool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ItemReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String userUuid;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Date reservationStart;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Date reservationEnd;

    @Transient
    private User user;
}
