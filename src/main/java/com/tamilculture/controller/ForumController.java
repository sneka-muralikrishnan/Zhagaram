package com.tamilculture.controller;

import com.tamilculture.model.ForumComment;
import com.tamilculture.model.ForumPost;
import com.tamilculture.model.User;
import com.tamilculture.service.ForumService;
import com.tamilculture.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {
    
    private final ForumService forumService;
    private final UserService userService;
    
    @GetMapping
    public String forumHome(Model model) {
        model.addAttribute("posts", forumService.getAllPosts());
        return "forum/index";
    }
    
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        ForumPost post = forumService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", forumService.getCommentsByPost(id));
        model.addAttribute("newComment", new ForumComment());
        return "forum/view";
    }
    
    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new ForumPost());
        return "forum/new";
    }
    
    @PostMapping("/new")
    public String createPost(@Valid @ModelAttribute("post") ForumPost post, 
                            BindingResult result, 
                            Authentication authentication) {
        if (result.hasErrors()) {
            return "forum/new";
        }
        
        User user = userService.findByUsername(authentication.getName());
        post.setAuthor(user);
        
        ForumPost savedPost = forumService.savePost(post);
        return "redirect:/forum/" + savedPost.getId();
    }
    
    @PostMapping("/{postId}/comment")
    public String addComment(@PathVariable Long postId, 
                            @Valid @ModelAttribute("newComment") ForumComment comment, 
                            BindingResult result, 
                            Authentication authentication,
                            Model model) {
        if (result.hasErrors()) {
            ForumPost post = forumService.getPostById(postId);
            model.addAttribute("post", post);
            model.addAttribute("comments", forumService.getCommentsByPost(postId));
            return "forum/view";
        }
        
        User user = userService.findByUsername(authentication.getName());
        ForumPost post = forumService.getPostById(postId);
        
        comment.setAuthor(user);
        comment.setPost(post);
        
        forumService.saveComment(comment);
        return "redirect:/forum/" + postId;
    }
    
    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", forumService.getPostById(id));
        return "forum/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, 
                            @Valid @ModelAttribute("post") ForumPost post, 
                            BindingResult result) {
        if (result.hasErrors()) {
            return "forum/edit";
        }
        
        ForumPost existingPost = forumService.getPostById(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        
        forumService.savePost(existingPost);
        return "redirect:/forum/" + id;
    }
    
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        forumService.deletePost(id);
        return "redirect:/forum";
    }
    
    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        ForumComment comment = forumService.getCommentsByPost(id).get(0);
        Long postId = comment.getPost().getId();
        forumService.deleteComment(id);
        return "redirect:/forum/" + postId;
    }
}
