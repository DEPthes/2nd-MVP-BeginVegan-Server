package com.beginvegan.domain.food.dto;

import com.beginvegan.domain.user.domain.VeganType;
import lombok.Builder;
import lombok.Data;

@Data
public class FoodListRes {

    private Long id;

    private String name;

    private VeganType veganType;

    private String description;

    @Builder
    public FoodListRes(Long id, String name, VeganType veganType, String description) {
        this.id = id;
        this.name = name;
        this.veganType = veganType;
        this.description = description;
    }
}
