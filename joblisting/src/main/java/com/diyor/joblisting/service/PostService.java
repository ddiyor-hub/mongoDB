package com.diyor.joblisting.service;

import com.diyor.joblisting.model.Post;
import com.diyor.joblisting.repo.PostRepo;
import com.diyor.joblisting.repo.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private SearchRepo searchRepo;

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post addPost(Post post) {
        postRepo.save(post);
        return post;
    }

    public List<Post> search(String text) {
        return searchRepo.findByText(text);
    }
}
