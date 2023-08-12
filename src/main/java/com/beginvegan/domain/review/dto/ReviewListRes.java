package com.beginvegan.domain.review.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ReviewListRes {

    List<?> reviews;

    @Builder
    public ReviewListRes(List<?> reviews) {
        this.reviews = reviews;
    }

}
