package com.tamilculture.service;

import com.tamilculture.model.ForumComment;
import com.tamilculture.model.ForumPost;
import com.tamilculture.repository.ForumCommentRepository;
import com.tamilculture.repository.ForumPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    
    private final ForumPostRepository forumPostRepository;
    private final ForumCommentRepository forumCommentRepository;
    
    // Post methods
    public List<ForumPost> getAllPosts() {
        return forumPostRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public ForumPost getPostById(Long id) {
        return forumPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forum post not found with id: " + id));
    }
    
    public List<ForumPost> getPostsByUser(Long userId) {
        return forumPostRepository.findByAuthorId(userId);
    }
    
    public ForumPost savePost(ForumPost post) {
        return forumPostRepository.save(post);
    }
    
    public void deletePost(Long id) {
        forumPostRepository.deleteById(id);
    }
    
    // Comment methods
    public List<ForumComment> getCommentsByPost(Long postId) {
        return forumCommentRepository.findByPostId(postId);
    }
    
    public ForumComment saveComment(ForumComment comment) {
        return forumCommentRepository.save(comment);
    }
    
    public void deleteComment(Long id) {
        forumCommentRepository.deleteById(id);
    }
}
