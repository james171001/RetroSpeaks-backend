package com.champ.retrospeaks.controller;



import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group/{groupId}/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping ()
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto,@PathVariable String postId)
    {

        commentService.create(commentDto,postId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/{commentId}")
    public ResponseEntity<Void> replyOnComment(@PathVariable("commentId") String commentId, @PathVariable String postId,
                                              @RequestBody CommentDto commentDto)
    {
        commentDto.setParentCommentID(commentId);
        commentService.create(commentDto,postId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") String commentId,
                                              @RequestBody CommentDto commentDto)
    {
        commentDto.setId(commentId);
        commentService.update(commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") String commentId)
    {
        commentService.delete(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}