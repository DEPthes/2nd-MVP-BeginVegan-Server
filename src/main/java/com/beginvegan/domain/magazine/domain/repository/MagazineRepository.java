package com.beginvegan.domain.magazine.domain.repository;

import com.beginvegan.domain.magazine.domain.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
}
