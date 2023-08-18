package com.beginvegan.domain.restaurant.dto;

import com.beginvegan.domain.restaurant.domain.Address;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantListRes {

    private Long id;

    private String name;

    private String businessHours;

    private Address address;

    private String imageUrl;

    private List<MenuDto> menus = new ArrayList<>();

    @Builder
    public RestaurantListRes(Long id, String name, String businessHours, Address address, String imageUrl, List<MenuDto> menus) {
        this.id = id;
        this.name = name;
        this.businessHours = businessHours;
        this.address = address;
        this.imageUrl = imageUrl;
        this.menus = menus;
    }
}
