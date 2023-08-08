package com.beginvegan.domain.user.dto;

import com.beginvegan.domain.user.domain.Provider;
import com.beginvegan.domain.user.domain.Role;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDetailRes {

    private Long id;

    private String name;

    private String email;

    private String imageUrl;

    private Provider provider;

    private Role role;

    @Builder
    public UserDetailRes(Long id, String name, String email, String imageUrl, Provider provider, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.role = role;
    }

}
