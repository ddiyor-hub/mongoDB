package com.diyor.joblisting.controller;

import com.diyor.joblisting.model.Post;
import com.diyor.joblisting.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test 12");
    }
    @GetMapping("/allPosts")
    @CrossOrigin
    public ResponseEntity<List<Post>> getAllPosts(){
        try {
            return ResponseEntity.ok(postService.getAllPosts());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/posts/{text}")
    @CrossOrigin
    public ResponseEntity<List<Post>> search(@PathVariable String text){
        if (text != null) {
            return ResponseEntity.ok(postService.search(text));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts")
   public ResponseEntity<Post> addPost(@RequestBody Post post){
        if (post != null) {
            Post addPost = postService.addPost(post);
            return ResponseEntity.ok(addPost);
        } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
