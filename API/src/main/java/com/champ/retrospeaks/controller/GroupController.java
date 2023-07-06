package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("")
    public ResponseEntity<List<GroupDto>> createGroup(@RequestBody GroupForCreationDto groupForCreationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        groupService.create(groupForCreationDto);
        List<GroupDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }
}
