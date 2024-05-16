package com.example.webshopcosmetics.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long about_id;

    @Column(name = "name_shop")
    private String nameShop;

    @Column(name = "introduce", columnDefinition = "TEXT")
    private String introduce;

    @Column(name = "information", columnDefinition = "TEXT")
    private String information;

    @Column(name = "address")
    private String address;

    @Column(name = "google_map", columnDefinition = "TEXT")
    private String googleMap;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "fanpage")
    private String fanpage;

    @Column(name = "founding")
    private Date founding;
}
