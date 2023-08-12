package com.beginvegan.domain.bookmark.dto;

import com.beginvegan.domain.restaurant.dto.RestaurantDetailRes;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BookmarkListRes {

    private List<RestaurantDetailRes> restaurants;

    @Builder
    public BookmarkListRes(List<RestaurantDetailRes> restaurants) {
        this.restaurants = restaurants;
    }

}

