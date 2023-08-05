package com.beginvegan.domain.bookmark.domain;

import com.beginvegan.domain.common.BaseEntity;
import com.beginvegan.domain.restaurant.domain.Restaurant;
import com.beginvegan.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Bookmark extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    public Bookmark(Long id, User user, Restaurant restaurant) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
    }

}
