package com.tamilculture.repository;

import com.tamilculture.model.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
    List<ForumComment> findByPostId(Long postId);
    List<ForumComment> findByAuthorId(Long authorId);
}
