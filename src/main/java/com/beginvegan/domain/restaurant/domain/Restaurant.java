package com.beginvegan.domain.restaurant.domain;

import com.beginvegan.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Restaurant extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String businessHours;

    private String contactNumber;

    @Embedded
    private Address address;

    private String latitude;

    private String longitude;

    private String kakaoMapUrl;

    @OneToMany(mappedBy = "restaurant")
    List<Menu> menus;

    @Builder
    public Restaurant(Long id, String name, String businessHours, String contactNumber, Address address, String latitude, String longitude, String kakaoMapUrl, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.businessHours = businessHours;
        this.contactNumber = contactNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kakaoMapUrl = kakaoMapUrl;
        this.menus = menus;
    }

}
