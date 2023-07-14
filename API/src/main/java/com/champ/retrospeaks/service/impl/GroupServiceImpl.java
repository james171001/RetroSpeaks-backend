package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.mapper.GroupMapper;
import com.champ.retrospeaks.model.Group;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.GroupRepository;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;


    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GroupDto getById(Long id) {
        Group group;
        try {
            group = groupRepository.getReferenceById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Group does not exist");
        }

        return GroupMapper.toGroupDto(group);
    }

    @Override
    public List<GroupDto> findAll() {
        List<Group> groups = groupRepository.findAll();

        if (groups.isEmpty()) {
            return Collections.emptyList();
        }

        return groups.stream()
                .map(GroupMapper::toGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isGroupExist(Long id) {
        boolean isExist;
        isExist = groupRepository.existsById(id);
        return isExist;


    }


//    @Override
//    @Transactional
//    public void create(GroupForCreationDto groupForCreationDto, String userName) {
//        User owner = userRepository.findByUserName(userName)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid User Name"));
//
//        Group group = GroupMapper.toGroup(groupForCreationDto);
//        group.setGroupOwner(owner.getId());
//        group.setCategoryType(groupForCreationDto.getCategoryType());
//
//        try {
//            groupRepository.save(group);
//        } catch (Exception e) {
//            // Handle the exception appropriately, e.g., log or rethrow
//            throw new RuntimeException("Failed to create group.", e);
//        }
//    }

    @Override
    @Transactional
    public void create(GroupForCreationDto groupForCreationDto, String userName) {
        User owner = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Name"));

        Group group = GroupMapper.toGroup(groupForCreationDto);
        group.setGroupOwner(owner.getId());

        // Check if categoryType is null or empty
        if (groupForCreationDto.getCategoryType() == null || groupForCreationDto.getCategoryType().equals("")) {
            throw new IllegalArgumentException("Category type is required.");
        }

        group.setCategoryType(groupForCreationDto.getCategoryType());

        try {
            groupRepository.save(group);
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., log or rethrow
            throw new RuntimeException("Failed to create group.", e);
        }
    }


//    @Override
//    @Transactional
//    public void update(GroupForCreationDto groupForCreationDto, String userName, Long groupId) {
//        User owner = userRepository.findByUserName(userName)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid User Name"));
//
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Group ID"));
//
//        if (!group.getGroupOwner().equals(owner.getId())) {
//            throw new IllegalArgumentException("Only the group owner can update the group.");
//        }
//        if (groupRepository.existsByNameIgnoreCaseAndIdNot(groupForCreationDto.getName(),groupId)) {
//            throw new IllegalArgumentException("Group name must be unique.");
//        }
//        group.setName(groupForCreationDto.getName());
//        group.setDescription(group.getDescription());
//
//        try {
//            groupRepository.save(group);
//        } catch (Exception e) {
//            // Handle the exception appropriately, e.g., log or rethrow
//            throw new RuntimeException("Failed to update group.", e);
//        }
//    }

    @Override
    @Transactional
    public void update(GroupForCreationDto groupForCreationDto, String userName, Long groupId) {
        User owner = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Name"));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Group ID"));

        if (!group.getGroupOwner().equals(owner.getId())) {
            throw new IllegalArgumentException("Only the group owner can update the group.");
        }
        if (groupRepository.existsByNameIgnoreCaseAndIdNot(groupForCreationDto.getName(), groupId)) {
            throw new IllegalArgumentException("Group name must be unique.");
        }
        group.setName(groupForCreationDto.getName());
        group.setDescription(groupForCreationDto.getDescription()); // Modified line

        try {
            groupRepository.save(group);
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., log or rethrow
            throw new RuntimeException("Failed to update group.", e);
        }
    }


    @Override
    public List<GroupDto> findGroupsByUser(String owner) {

        User user = userRepository.findByUserName(owner).orElseThrow( () -> new IllegalArgumentException("Invalid User"));

        List<Group> groups = groupRepository.findByUsers_Id(user.getId());

        return groups.stream()
                .map(GroupMapper::toGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void followGroup(String userName, Long groupId) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group does not exist"));

        if (!group.getUsers().contains(user)) {
            group.getUsers().add(user);
            user.getGroups().add(group);
        }

        groupRepository.save(group);
        userRepository.save(user);
    }



    @Override
    @Transactional
    public void unfollowGroup(String userName, Long groupId) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new IllegalArgumentException("User does not exist"));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group does not exist"));

        if (group.getUsers().contains(user)) {
            group.getUsers().remove(user);
            user.getGroups().remove(group);
        }

        groupRepository.save(group);
        userRepository.save(user);
    }


}
