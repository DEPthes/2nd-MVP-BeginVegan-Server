package com.beginvegan.domain.restaurant.domain.repository;

import com.beginvegan.domain.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
