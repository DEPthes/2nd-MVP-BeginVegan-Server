package com.beginvegan.domain.restaurant.application;

import com.beginvegan.domain.bookmark.domain.Bookmark;
import com.beginvegan.domain.bookmark.domain.repository.BookmarkRepository;
import com.beginvegan.domain.bookmark.exception.ExistsBookmarkException;
import com.beginvegan.domain.bookmark.exception.NotExistsBookmarkException;
import com.beginvegan.domain.restaurant.domain.Menu;
import com.beginvegan.domain.restaurant.domain.Restaurant;
import com.beginvegan.domain.restaurant.domain.repository.MenuRepository;
import com.beginvegan.domain.restaurant.domain.repository.RestaurantRepository;
import com.beginvegan.domain.restaurant.dto.*;
import com.beginvegan.domain.restaurant.exception.InvalidRestaurantException;
import com.beginvegan.domain.review.domain.Review;
import com.beginvegan.domain.review.domain.repository.ReviewRepository;
import com.beginvegan.domain.review.dto.RestaurantReviewDetailRes;
import com.beginvegan.domain.review.dto.ReviewDetailRes;
import com.beginvegan.domain.review.dto.ReviewListRes;
import com.beginvegan.domain.user.domain.User;
import com.beginvegan.domain.user.domain.repository.UserRepository;
import com.beginvegan.domain.user.exception.InvalidUserException;
import com.beginvegan.global.config.security.token.UserPrincipal;
import com.beginvegan.global.payload.ApiResponse;
import com.beginvegan.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public ResponseEntity<?> findRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId)
                .orElseThrow(InvalidRestaurantException::new);

        RestaurantAndMenusRes restaurantAndMenusRes = RestaurantAndMenusRes.builder()
                .restaurant(RestaurantDetailRes.toDto(restaurant))
                .menus(restaurant.getMenus().stream()
                        .map(MenuDetailRes::toDto)
                        .toList())
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(restaurantAndMenusRes)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> findRestaurantReviewsById(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(InvalidRestaurantException::new);

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "date"));
        Page<Review> reviewPage = reviewRepository.findReviewsByRestaurant(restaurant, pageRequest);

        List<Review> reviews = reviewPage.getContent();
        List<RestaurantReviewDetailRes> reviewDetailRes = reviews.stream()
                .map(review -> RestaurantReviewDetailRes.builder()
                        .id(review.getId())
                        .content(review.getContent())
                        .date(review.getDate())
                        .user(review.getUser())
                        .build())
                .toList();

        ReviewListRes reviewListRes = ReviewListRes.builder()
                .reviews(reviewDetailRes)
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(reviewListRes)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Transactional
    public ResponseEntity<?> scrapRestaurant(UserPrincipal userPrincipal, Long restaurantId) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(InvalidUserException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(InvalidRestaurantException::new);

        if (bookmarkRepository.existsBookmarkByUserAndRestaurant(user, restaurant)) {
            throw new ExistsBookmarkException();
        }

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .restaurant(restaurant)
                .build();

        bookmarkRepository.save(bookmark);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("스크랩 되었습니다.").build())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Transactional
    public ResponseEntity<?> deleteScrapRestaurant(UserPrincipal userPrincipal, Long restaurantId) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(InvalidUserException::new);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(InvalidRestaurantException::new);

        if (!bookmarkRepository.existsBookmarkByUserAndRestaurant(user, restaurant)) {
            throw new NotExistsBookmarkException();
        }

        Bookmark bookmark = bookmarkRepository.findBookmarkByUserAndRestaurant(user, restaurant);
        bookmarkRepository.delete(bookmark);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("스크랩 해제되었습니다.").build())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Transactional
    public ResponseEntity<?> findAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantListRes> restaurantDtos = new ArrayList<>();

        for(Restaurant restaurant : restaurants) {
            List<MenuDto> menuDtos = restaurant.getMenus().stream()
                    .map(menu -> MenuDto.builder()
                            .id(menu.getId())
                            .imageUrl(menu.getImageUrl())
                            .build())
                    .collect(Collectors.toList());

            RestaurantListRes restaurantListRes = RestaurantListRes.builder()
                    .id(restaurant.getId())
                    .name(restaurant.getName())
                    .businessHours(restaurant.getBusinessHours())
                    .address(restaurant.getAddress())
                    .imageUrl(restaurant.getImageUrl())
                    .menus(menuDtos)
                    .build();

            restaurantDtos.add(restaurantListRes);
        }

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(restaurantDtos)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
