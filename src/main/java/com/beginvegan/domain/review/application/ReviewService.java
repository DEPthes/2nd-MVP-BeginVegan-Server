package com.beginvegan.domain.review.application;

import com.beginvegan.domain.restaurant.dto.RestaurantDetailRes;
import com.beginvegan.domain.review.domain.Review;
import com.beginvegan.domain.review.domain.repository.ReviewRepository;
import com.beginvegan.domain.review.dto.ReviewDetailRes;
import com.beginvegan.domain.user.domain.User;
import com.beginvegan.domain.user.domain.repository.UserRepository;
import com.beginvegan.domain.user.dto.UserDetailRes;
import com.beginvegan.domain.user.exception.InvalidUserException;
import com.beginvegan.global.config.security.token.UserPrincipal;
import com.beginvegan.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> findReviewsByUser(UserPrincipal userPrincipal, Integer page) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(InvalidUserException::new);

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "date"));
        Page<Review> reviewPage = reviewRepository.findReviewsByUser(user, pageRequest);

        List<Review> reviews = reviewPage.getContent();

        List<ReviewDetailRes> reviewDetailResList = reviews.stream()
                .map(review -> ReviewDetailRes.builder()
                        .id(review.getId())
                        .content(review.getContent())
                        .date(review.getDate())
                        .restaurantDetailRes(RestaurantDetailRes.toDto(review.getRestaurant()))
                        .userDetailRes(UserDetailRes.toDto(user))
                        .build())
                .toList();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(reviewDetailResList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
