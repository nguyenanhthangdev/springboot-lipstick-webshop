package com.example.webshopcosmetics.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long banner_id;

    @Column(name = "image")
    private String image;

    @Column(name = "option")
    private BannerOption option;

    @Column(name = "status")
    private boolean status;

    @Column(name = "link")
    private String link;
}
