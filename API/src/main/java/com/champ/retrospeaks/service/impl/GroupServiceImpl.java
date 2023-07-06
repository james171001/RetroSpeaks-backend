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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;



    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;

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

        return groups.stream().map(GroupMapper::toGroupDto).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public  void create (GroupForCreationDto groupForCreationDto) {

        Group group = GroupMapper.toGroup(groupForCreationDto);

        groupRepository.save(group);




    }
}
