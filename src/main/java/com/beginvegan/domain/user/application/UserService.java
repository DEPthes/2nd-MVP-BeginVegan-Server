package com.beginvegan.domain.user.application;

import com.beginvegan.domain.user.domain.repository.UserRepository;
import com.beginvegan.global.config.security.token.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getUserByEmail(UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userPrincipal);
    }

}
