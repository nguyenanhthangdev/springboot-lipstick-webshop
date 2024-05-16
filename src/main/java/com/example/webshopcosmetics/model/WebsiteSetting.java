package com.example.webshopcosmetics.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "website_setting")
public class WebsiteSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long setting_id;

    @Column(name = "setting_key") // Thay đổi tên cột "key" thành "setting_key"
    private String key;

    @Column(name = "setting_value", columnDefinition = "TEXT")
    private String value;
}