package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Post.PostDataDto;
import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.PostData;
import com.champ.retrospeaks.service.PostDataService;
import com.champ.retrospeaks.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.champ.retrospeaks.mapper.toPostDataDto.toPostDataDto;

@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;
    private final PostDataService postDataService;

    @Autowired
    public PostController(PostService postService, PostDataService postDataService) {
        this.postService = postService;
        this.postDataService = postDataService;
    }

    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) throws Exception {
        try{
            postService.savePost(postDto);
            return ResponseEntity.ok(postDto);
        }catch (Exception e){
            throw new Exception("Error Saving Post "+ e.getMessage());
        }
    }


    @GetMapping("/view-post/{id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable String id) throws Exception {
        try{
            PostDto postDto = postService.getPostById(id);
            postDataService.updatePostDataInteraction(id);
            return ResponseEntity.ok(postDto);
        }catch (Exception e){
            throw new Exception("Error Getting Post "+ e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<PostDto>> getPostsByGroupId(@PathVariable int groupId) throws Exception {
        try{
            List<PostDto> posts = postService.getAllPostByGroupId(groupId);
            return ResponseEntity.ok(posts);
        }catch (Exception e){
            throw new Exception("Error Getting Post "+ e.getMessage());
        }

    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<PostDto>> getAllPostByUSerID(@PathVariable Long userID) throws Exception {
        try{
            List<PostDto> posts = postService.getAllPostByUserID(userID);
            return ResponseEntity.ok(posts);
        }catch (Exception e){
            throw new Exception("Error Getting Post "+ e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost() throws Exception {
        try{
            List<PostDto> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        }catch (Exception e){
            throw new Exception("Error Getting Post "+ e.getMessage());
        }
    }

    @GetMapping("/agreeToPost/{id}")
    public ResponseEntity<PostDto> voteAgreeToPost(@PathVariable String id) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PostDto> postOptional = postService.agreePost(id, username);

        if(!postDataService.findPostDataById(id).isPresent()){
            PostDataDto postDataDto = new PostDataDto();
            postDataDto.setId(id);
            postDataDto.setInteractionCount(1);
            postDataService.savePostData(postDataDto);
        }
        else{
            Optional<PostData> postData = postDataService.findPostDataById(id);
            postData.get().setInteractionCount(postData.get().getInteractionCount()+1);
            postDataService.savePostData(toPostDataDto(postData));
        }

        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/disagreeToPost/{id}")
    public ResponseEntity<PostDto> voteDisagreeToPost(@PathVariable String id) throws Exception {
       String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PostDto> postOptional = postService.disAgreePost(id, username);
        postDataService.updatePostDataInteraction(id);
        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<Void> deletePost(@PathVariable String id) throws Exception {
        try{
            postService.deletePostById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            throw new Exception("Error Deleting Post "+ e.getMessage());
        }
    }

}
