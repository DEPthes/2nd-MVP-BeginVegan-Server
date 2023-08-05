package com.beginvegan.domain.bookmark.domain.repository;

import com.beginvegan.domain.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
