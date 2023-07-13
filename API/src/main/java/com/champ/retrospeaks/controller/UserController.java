package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.PostService;
import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserService userService, PostService postService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.postService = postService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/{userId}/Post")
    public ResponseEntity<Void> createUserPost(@RequestBody PostDto postDto){
        postService.savePost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{userId}/Post")
    public ResponseEntity<List<PostDto>> getAllPostByUserID(@PathVariable Long userID){
        List<PostDto> posts = postService.getAllPostByUserID(userID);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{userId}/Post/{postId}")
    public ResponseEntity<PostDto>updatePost(@PathVariable String postId,@PathVariable Long userId, @RequestBody PostDto postDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = userService.findUserByUsername(username);

        postDto.setId(postId); // Set the ID of the postDto based on the path variable
        if(!Objects.equals(currentUser.get().getId(), postService.getPostById(postId).getUserID())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<PostDto> updatedPostOptional = postService.updatePost(postDto);

        if (updatedPostOptional.isPresent()) {
            PostDto updatedPost = updatedPostOptional.get();
            return ResponseEntity.ok(updatedPost);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/Post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId, @PathVariable Long userId) {

        PostDto currentPost = postService.getPostById(postId);
        if(!Objects.equals(currentPost.getUserID(), userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>>getAllPost(){
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    //EDITING A PROFILE
    @PutMapping("/{userId}/edit-profile")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = existingUserOptional.get();

        existingUser.setPassWord(passwordEncoder.encode(updatedUser.getPassword()));
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setGender(updatedUser.getGender());

        User updatedUserProfile = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUserProfile);
    }

}
