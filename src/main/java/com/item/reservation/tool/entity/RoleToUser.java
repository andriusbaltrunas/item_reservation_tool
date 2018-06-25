package com.item.reservation.tool.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RoleToUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private int userRoleId;
}
