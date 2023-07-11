//package com.champ.retrospeaks.service.impl;
//
//import com.champ.retrospeaks.dto.Post.PostDto;
//import com.champ.retrospeaks.model.Post;
//import com.champ.retrospeaks.model.User;
//import com.champ.retrospeaks.repository.PostRepository;
//import com.champ.retrospeaks.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PostServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PostRepository postRepository;
//
//    @InjectMocks
//    private PostServiceImpl postService;
//
//    @Test
//    void savePost() {
//        PostDto postDto = new PostDto();
//        postDto.setId("1");
//        postDto.setUserID(1L);
//        postDto.setUsername("john_doe");
//        postDto.setPostType(1);
//        postDto.setTitle("Sample Post");
//        postDto.setContent("This is a sample post.");
//        postDto.setPostDate(LocalDateTime.now());
//        postDto.setCategory("General");
//        postDto.setGroupId(1);
//        postDto.setAgreeCount(0);
//        postDto.setDisagreeCount(0);
//
//        // Create a sample user
//        User user = new User();
//        user.setId(1L);
//        user.setUserName("john_doe");
//        user.setPassWord("password123");
//        user.setEmail("john.doe@example.com");
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setGender("Male");
//        user.setCreatedDate(LocalDate.now());
//
//
//        // Mock the SecurityContextHolder behavior
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        Mockito.when(authentication.getName()).thenReturn(user.getUsername());
//
//        // Mock the UserRepository behavior
//        when(userRepository.findByUserName(any())).thenReturn(Optional.of(user));
//
//        // Call the savePost method
//        postService.savePost(postDto);
//
//        // Verify that the userRepository.findByUserName method was called
//        verify(userRepository, times(1)).findByUserName(any());
//
//        // Verify that the postRepository.save method was called
//        verify(postRepository, times(1)).save(any());
//    }
//
//
//    @Test
//    void getALlPosts() {
//        // Create a sample list of posts
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post());
//        posts.add(new Post());
//
//        // Mock the postRepository behavior
//        when(postRepository.findAll()).thenReturn(posts);
//
//        // Call the getAllPosts method
//        List<PostDto> result = postService.getAllPosts();
//
//        // Verify that the postRepository.findAll method was called
//        verify(postRepository, times(1)).findAll();
//
//        // Assert the result
//        assertEquals(posts.size(), result.size());
//    }
//
//
//    @Test
//    void agreePost() {
//        // Create a sample post
//        Post post = new Post();
//        post.setId("1");
//        post.setUserID(1L);
//        post.setUsername("john_doe");
//        post.setPostType(1);
//        post.setTitle("Sample Post");
//        post.setContent("This is a sample post.");
//        post.setPostDate(LocalDateTime.now());
//        post.setCategory("General");
//        post.setGroupId(1);
//        post.setAgreeCount(0);
//        post.setDisagreeCount(0);
//
//        // Mock the postRepository behavior
//        when(postRepository.findById("1")).thenReturn(Optional.of(post));
//
//        // Call the agreePost method
//        Optional<PostDto> result1 = postService.agreePost("1", "john_doe");
//
//        // Verify that the postRepository.findById method was called
//        verify(postRepository, times(1)).findById("1");
//
//        // Assert that the result is present
//        assertTrue(result1.isPresent());
//
//        // Perform other assertions on the fields of the PostDto object
//        assertEquals(post.getUsername(), result1.get().getUsername());
//        assertEquals(post.getPostType(), result1.get().getPostType());
//        assertEquals(post.getTitle(), result1.get().getTitle());
//        assertEquals(post.getContent(), result1.get().getContent());
//        assertEquals(post.getPostDate(), result1.get().getPostDate());
//        assertEquals(post.getCategory(), result1.get().getCategory());
//        assertEquals(post.getGroupId(), result1.get().getGroupId());
//        assertEquals(post.getAgreeCount(), result1.get().getAgreeCount());
//        assertEquals(post.getDisagreeCount(), result1.get().getDisagreeCount());
//    }
//
//    @Test
//    void disAgreePost() {
//        // Create a sample post
//        Post post = new Post();
//        post.setId("1");
//        post.setUserID(1L);
//        post.setUsername("john_doe");
//        post.setPostType(1);
//        post.setTitle("Sample Post");
//        post.setContent("This is a sample post.");
//        post.setPostDate(LocalDateTime.now());
//        post.setCategory("General");
//        post.setGroupId(1);
//        post.setAgreeCount(0);
//        post.setDisagreeCount(0);
//
//        // Mock the postRepository behavior
//        when(postRepository.findById("1")).thenReturn(Optional.of(post));
//
//        // Call the disagreePost method
//        Optional<PostDto> result1 = postService.disAgreePost("1", "john_doe");
//
//        // Verify that the postRepository.findById method was called
//        verify(postRepository, times(1)).findById("1");
//
//        // Assert that the result is present
//        assertTrue(result1.isPresent());
//
//        // Perform other assertions on the fields of the PostDto object
//        assertEquals(post.getUsername(), result1.get().getUsername());
//        assertEquals(post.getPostType(), result1.get().getPostType());
//        assertEquals(post.getTitle(), result1.get().getTitle());
//        assertEquals(post.getContent(), result1.get().getContent());
//        assertEquals(post.getPostDate(), result1.get().getPostDate());
//        assertEquals(post.getCategory(), result1.get().getCategory());
//        assertEquals(post.getGroupId(), result1.get().getGroupId());
//        assertEquals(post.getAgreeCount(), result1.get().getAgreeCount());
//        assertEquals(post.getDisagreeCount(), result1.get().getDisagreeCount());
//    }
//
//    @Test
//    void updatePost() {
//        // Create a sample post
//        Post post = new Post();
//        post.setId("1");
//        post.setTitle("Sample Post");
//        post.setContent("This is a sample post.");
//
//        // Create a sample postDto for update
//        PostDto postDto = new PostDto();
//        postDto.setId("1");
//        postDto.setTitle("Updated Post");
//        postDto.setContent("This is an updated post.");
//
//        // Mock the postRepository behavior
//        when(postRepository.findById("1")).thenReturn(Optional.of(post));
//        when(postRepository.save(any())).thenReturn(post);
//
//        // Call the updatePost method
//        Optional<PostDto> result = postService.updatePost(postDto);
//
//        // Verify that the postRepository.findById method was called
//        verify(postRepository, times(1)).findById("1");
//
//        // Verify that the postRepository.save method was called
//        verify(postRepository, times(1)).save(post);
//
//        // Assert the result
//        assertTrue(result.isPresent());
//        assertEquals(postDto.getTitle(), result.get().getTitle());
//        assertEquals(postDto.getContent(), result.get().getContent());
//    }
//}