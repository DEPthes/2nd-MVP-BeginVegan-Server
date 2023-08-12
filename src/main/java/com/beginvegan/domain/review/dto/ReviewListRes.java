package com.beginvegan.domain.review.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ReviewListRes {

    List<ReviewDetailRes> reviews;

    @Builder
    public ReviewListRes(List<ReviewDetailRes> reviews) {
        this.reviews = reviews;
    }

}
