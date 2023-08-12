package com.beginvegan.domain.restaurant.dto;

import com.beginvegan.domain.restaurant.domain.Address;
import com.beginvegan.domain.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
public class RestaurantDetailRes {

    private Long id;
    private String name;
    private String businessHours;
    private String contactNumber;
    private Address address;
    private String latitude;
    private String longitude;
    private String kakaoMapUrl;
    private String imageUrl;
    private String imageSource;

    @Builder
    public RestaurantDetailRes(Long id, String name, String businessHours, String contactNumber, Address address, String latitude, String longitude, String kakaoMapUrl, String imageUrl, String imageSource) {
        this.id = id;
        this.name = name;
        this.businessHours = businessHours;
        this.contactNumber = contactNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kakaoMapUrl = kakaoMapUrl;
        this.imageUrl = imageUrl;
        this.imageSource = imageSource;
    }

    public static RestaurantDetailRes toDto(Restaurant restaurant) {
        return RestaurantDetailRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .businessHours(restaurant.getBusinessHours())
                .contactNumber(restaurant.getContactNumber())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .kakaoMapUrl(restaurant.getKakaoMapUrl())
                .imageUrl(restaurant.getImageUrl())
                .imageSource(restaurant.getImageSource())
                .build();
    }

}
