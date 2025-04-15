package com.tamilculture.repository;

import com.tamilculture.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
    List<ForumPost> findByAuthorId(Long authorId);
    List<ForumPost> findAllByOrderByCreatedAtDesc();
}
