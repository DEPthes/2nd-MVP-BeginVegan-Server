package com.beginvegan.domain.category.domain.repository;

import com.beginvegan.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
