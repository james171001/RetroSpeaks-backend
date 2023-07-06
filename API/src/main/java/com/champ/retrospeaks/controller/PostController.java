package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<PostDto>> getPostsByGroupId(@PathVariable int groupId) {
        List<PostDto> posts = postService.getAllPostByGroupId(groupId);
        return ResponseEntity.ok(posts);
    }
}
