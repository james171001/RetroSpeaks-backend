package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group/{groupId}/post/{postId}")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping ("/comments/save")
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto)
    {
        commentService.create(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") String commentId,
                                              @RequestBody GroupForCreationDto groupDto,
                                              @RequestParam("owner") String owner) {
        commentService.update(groupDto, owner, Long.valueOf(commentId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}