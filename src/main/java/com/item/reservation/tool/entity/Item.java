package com.item.reservation.tool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String longDescription;

    @Column(nullable = false)
    private String imagePath1;

    private String imagePath2;

    private String imagePath3;

    private String imagePath4;

    @Transient
    private List<String> imagePaths;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "itemId")
    private List<ItemReservation> itemReservations;

    public List<String> getImagePaths() {
        if (imagePaths == null) {
            imagePaths = new ArrayList<>();
        }
        addImageToList(imagePath1);
        addImageToList(imagePath2);
        addImageToList(imagePath3);
        addImageToList(imagePath4);
        return imagePaths;
    }

    private void addImageToList(String imagePath) {
        if (imagePath != null && !imagePaths.contains(imagePath)) {
            imagePaths.add(imagePath);
        }
    }
}
