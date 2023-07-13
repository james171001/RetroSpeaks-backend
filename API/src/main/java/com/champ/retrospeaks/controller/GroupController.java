package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.dto.Post.PostDto;
import com.champ.retrospeaks.service.GroupService;

import com.champ.retrospeaks.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController

@RequestMapping("api/group")
public class GroupController {

    private final GroupService groupService;
    private final PostService postService;

    @Autowired
    public GroupController(GroupService groupService, PostService postService) {
        this.groupService = groupService;
        this.postService = postService;
    }

    //--------------- Group creation and modification starts here
    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        String owner = SecurityContextHolder.getContext().getAuthentication().getName();
        List<GroupDto> groups = groupService.findGroupsByUser(owner);
        return ResponseEntity.ok(groups);
    }
    @PostMapping
    public ResponseEntity<List<GroupDto>> createGroup(@RequestBody GroupForCreationDto groupForCreationDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null) {
            throw new IllegalArgumentException("No username found");
        }
        groupService.create(groupForCreationDto, userName);
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("groupId") Long id) {
        try {
            GroupDto group = groupService.getById(id);
            return ResponseEntity.ok(group);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<List<GroupDto>> updateGroup(@RequestBody GroupForCreationDto groupForCreationDto,
                                                      @PathVariable("groupId") Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null) {
            throw new IllegalArgumentException("No username found");
        }
        groupService.update(groupForCreationDto, userName, id);

        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{groupId}/follow")
    public ResponseEntity<List<GroupDto>> followGroup(@PathVariable("groupId") Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null) {
            throw new IllegalArgumentException("No username found");
        }
        groupService.followGroup(userName,id);
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{groupId}/unfollow")
    public ResponseEntity<List<GroupDto>> unfollowGroup(@PathVariable("groupId") Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName == null) {
            throw new IllegalArgumentException("No username found");
        }
        groupService.unfollowGroup(userName,id);

        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    //--------------- Group creation and modification ends here


    //--------------- Group-post creation and modification starts here
    @GetMapping("/{groupId}/post")
    public ResponseEntity<List<PostDto>> getPostsByGroupId(@PathVariable int groupId) {
        List<PostDto> posts = postService.getAllPostByGroupId(groupId);
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/{groupId}/post")
    public ResponseEntity<Void> savePost(@PathVariable int groupId, @RequestBody PostDto postDto){
        postDto.setGroupId(groupId);
        postService.savePost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{groupId}/post/agreeToPost/{postId}")
    public ResponseEntity<PostDto>voteAgreeToPost(@PathVariable String postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PostDto> postOptional = postService.agreePost(postId, username);
        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{groupId}/post/disagreeToPost/{postId}")
    public ResponseEntity<PostDto> voteDisagreeToPost(@PathVariable String postId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PostDto> postOptional = postService.disAgreePost(postId, username);
        if (postOptional.isPresent()) {
            PostDto postDto = postOptional.get();
            return ResponseEntity.ok(postDto);
        } else {
            // Handle the case where the post is not found
            return ResponseEntity.notFound().build();
        }
    }

}
