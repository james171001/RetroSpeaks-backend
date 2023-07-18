package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.PostRepository;
import com.champ.retrospeaks.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set up the authentication context
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testSavePost_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        String userName = "testUser";
        String title = "Test Post";
        String content = "This is a test post.";

        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);

        // Set the authenticated user in SecurityContextHolder
        Authentication auth = new UsernamePasswordAuthenticationToken(userName, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock the userRepository.findByUserName method to return an empty Optional<User>
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> postService.savePost(postDto));
        verify(postRepository, never()).save(any());
    }

    @Test
    void testAgreePost() {
        // Arrange
        Long userId = 1L;
        String currentUsername = "testUser";
        String postId = "post1";
        int initialAgreeCount = 5;

        User currentUser = new User();
        currentUser.setId(userId);
        currentUser.setUserName(currentUsername);

        List<Long> existingVoterList = new ArrayList<>();
        existingVoterList.add(userId);

        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setAgreeCount(initialAgreeCount);
        existingPost.setVoterList(existingVoterList);

        // Mock the userRepository.findByUserName method to return the current user
        when(userRepository.findByUserName(currentUsername)).thenReturn(Optional.of(currentUser));

        // Mock the postRepository.findById method to return the existing post
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // Act
        Optional<PostDto> result = postService.agreePost(postId, currentUsername);

        // Assert
        assertTrue(result.isPresent());
        PostDto postDto = result.get();
        assertEquals(postId, postDto.getId());
        assertEquals(initialAgreeCount, postDto.getAgreeCount());
    }

    @Test
    void testDisAgreePost() {
        // Arrange
        Long userId = 1L;
        String currentUsername = "testUser";
        String postId = "post1";
        int initialDisagreeCount = 3;

        User currentUser = new User();
        currentUser.setId(userId);
        currentUser.setUserName(currentUsername);

        List<Long> existingVoterList = new ArrayList<>();
        existingVoterList.add(userId);

        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setDisagreeCount(initialDisagreeCount);
        existingPost.setVoterList(existingVoterList);

        // Mock the userRepository.findByUserName method to return the current user
        when(userRepository.findByUserName(currentUsername)).thenReturn(Optional.of(currentUser));

        // Mock the postRepository.findById method to return the existing post
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // Act
        Optional<PostDto> result = postService.disAgreePost(postId, currentUsername);

        // Assert
        assertTrue(result.isPresent());
        PostDto postDto = result.get();
        assertEquals(postId, postDto.getId());
        assertEquals(initialDisagreeCount, postDto.getDisagreeCount());
    }

    @Test
    void testUpdatePost() {
        // Arrange
        String postId = "post1";
        String updatedTitle = "Updated Title";
        String updatedContent = "Updated Content";

        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setTitle("Original Title");
        existingPost.setContent("Original Content");

        PostDto updatedPostDto = new PostDto();
        updatedPostDto.setId(postId);
        updatedPostDto.setTitle(updatedTitle);
        updatedPostDto.setContent(updatedContent);

        // Mock the postRepository.findById method to return the existing post
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // Mock the postRepository.save method to return the updated post
        when(postRepository.save(existingPost)).thenReturn(existingPost);

        // Act
        Optional<PostDto> result = postService.updatePost(updatedPostDto);

        // Assert
        assertTrue(result.isPresent());
        PostDto postDto = result.get();
        assertEquals(postId, postDto.getId());
        assertEquals(updatedTitle, postDto.getTitle());
        assertEquals(updatedContent, postDto.getContent());
    }
}