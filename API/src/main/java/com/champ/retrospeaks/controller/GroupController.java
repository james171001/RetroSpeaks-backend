package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
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




    @PostMapping
    public ResponseEntity<List<GroupDto>> createGroup(@RequestBody GroupForCreationDto groupForCreationDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        groupService.create(groupForCreationDto,userName);
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }
}

