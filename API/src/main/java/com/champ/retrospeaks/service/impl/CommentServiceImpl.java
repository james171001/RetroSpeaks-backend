package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Comment.CommentDto;
import com.champ.retrospeaks.dto.Comment.CommentForCreationDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.model.Comment;
import com.champ.retrospeaks.model.Post;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.CommentRepository;
import com.champ.retrospeaks.repository.PostRepository;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.CommentService;
import com.champ.retrospeaks.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(CommentDto commentDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUserName(username);

        commentDto.setUserId(user.get().getId());
        commentDto.setCommentDate();

        // Save the comment entity
        commentRepository.save(commentMapper.toEntity(commentDto));
    }

    @Override
    public void update(GroupForCreationDto groupForCreationDto, String owner, Long groupId) {
        Comment comment = commentRepository.findById(groupId.toString())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        // Update the comment properties based on the provided GroupForCreationDto and owner
//        comment.setContent(groupForCreationDto.getContent());
        comment.setUpdatedDate(LocalDateTime.now());

        // Save the updated comment entity
        commentRepository.save(comment);
    }

}
