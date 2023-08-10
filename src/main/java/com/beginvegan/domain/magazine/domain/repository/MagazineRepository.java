package com.beginvegan.domain.magazine.domain.repository;

import com.beginvegan.domain.magazine.domain.Magazine;
import com.beginvegan.domain.magazine.domain.MagazineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    List<Magazine> findAllByMagazineType(MagazineType magazineType);

}
