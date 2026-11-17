package com.example.api.controller;

import com.example.api.dto.PostRequest;
import com.example.api.model.Post;
import com.example.api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Endpoints for managing blog posts and articles")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all posts", description = "Retrieves all posts with optional filtering by category or title search")
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(postService.getAllPosts(category, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by ID", description = "Retrieves a single post by its ID")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    @Operation(summary = "Create post", description = "Creates a new post with validation")
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request) {
        Post createdPost = postService.createPost(request);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update post", description = "Updates an existing post by ID")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        Post updatedPost = postService.updatePost(id, request);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post", description = "Deletes a post by ID")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/like")
    @Operation(summary = "Like post", description = "Increments like count for a post")
    public ResponseEntity<Post> likePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.likePost(id));
    }
}
