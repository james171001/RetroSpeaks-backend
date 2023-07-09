package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody PostDto postDto){
        postService.savePost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/view-post/{id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable String id){
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }



    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<PostDto>> getPostsByGroupId(@PathVariable int groupId) {
        List<PostDto> posts = postService.getAllPostByGroupId(groupId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<PostDto>> getAllPostByUSerID(@PathVariable Long userID){
        List<PostDto> posts = postService.getAllPostByUserID(userID);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/agreeToPost/{id}")
    public ResponseEntity<PostDto> voteAgreeToPost(@PathVariable String id) {
        Optional<PostDto> postOptional = postService.agreePost(id);
        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/disagreeToPost/{id}")
    public ResponseEntity<PostDto> voteDisagreeToPost(@PathVariable String id) {
        Optional<PostDto> postOptional = postService.disAgreePost(id);
        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable String id, @RequestBody PostDto postDto) {
        postDto.setId(id); // Set the ID of the postDto based on the path variable

        Optional<PostDto> updatedPostOptional = postService.updatePost(postDto);

        if (updatedPostOptional.isPresent()) {
            PostDto updatedPost = updatedPostOptional.get();
            return ResponseEntity.ok(updatedPost);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

}
