<<<<<<< Updated upstream
package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.dto.Comment.CommentForCreationDto;
import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group/{groupId}/post/{postId}")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
=======
//package com.champ.retrospeaks.controller;
//
//
//import com.champ.retrospeaks.dto.Comment.CommentForCreationDto;
//import com.champ.retrospeaks.dto.Group.GroupDto;
//import com.champ.retrospeaks.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.NonNull;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/group/{groupId}/post/{postId}")
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @Autowired
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
>>>>>>> Stashed changes


//    @PostMapping
//    public ResponseEntity<CommentForCreationDto> createComment(CommentForCreationDto comment, @NonNull @PathVariable Long groupId,@NonNull @PathVariable String postId){
//
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (userName == null) {
//            throw new IllegalArgumentException("No username found");
//        }
//        commentService.create(comment, userName, postId);
//
//
//    }
<<<<<<< Updated upstream

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
=======
//}
>>>>>>> Stashed changes
