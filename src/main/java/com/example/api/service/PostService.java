package com.example.api.service;

import com.example.api.dto.PostRequest;
import com.example.api.exception.ResourceNotFoundException;
import com.example.api.model.Post;
import com.example.api.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(String category, String search) {
        if (category != null && !category.isBlank()) {
            return postRepository.findByCategoryIgnoreCase(category);
        }
        if (search != null && !search.isBlank()) {
            return postRepository.findByTitleContainingIgnoreCase(search);
        }
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    public Post createPost(PostRequest request) {
        Post post = new Post(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getCategory()
        );
        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostRequest request) {
        Post post = getPostById(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(request.getAuthor());
        post.setCategory(request.getCategory());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    public Post likePost(Long id) {
        Post post = getPostById(id);
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }
}
