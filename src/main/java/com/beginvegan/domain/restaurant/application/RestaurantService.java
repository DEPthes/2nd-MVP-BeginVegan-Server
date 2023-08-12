package com.beginvegan.domain.restaurant.application;

import com.beginvegan.domain.restaurant.domain.Restaurant;
import com.beginvegan.domain.restaurant.domain.repository.MenuRepository;
import com.beginvegan.domain.restaurant.domain.repository.RestaurantRepository;
import com.beginvegan.domain.restaurant.dto.MenuDetailRes;
import com.beginvegan.domain.restaurant.dto.RestaurantAndMenusRes;
import com.beginvegan.domain.restaurant.dto.RestaurantDetailRes;
import com.beginvegan.domain.restaurant.exception.InvalidRestaurantException;
import com.beginvegan.global.config.security.token.UserPrincipal;
import com.beginvegan.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

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

}
