package com.beginvegan.domain.food.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FoodIngredientDto {

    private Long id;

    private String name;

    @Builder
    public FoodIngredientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
