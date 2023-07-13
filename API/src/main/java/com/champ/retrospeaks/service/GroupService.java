package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import org.springframework.data.mongodb.gridfs.GridFsUpload;

import java.util.List;

public interface GroupService {

    GroupDto getById(Long id);

    List<GroupDto> findAll();

     Boolean isGroupExist(Long id);

     void create (GroupForCreationDto groupForCreationDto, String owner);

     void update (GroupForCreationDto groupForCreationDto, String owner, Long groupId);

     List<GroupDto> findGroupsByUser(String owner);

     void followGroup(String userName,Long groupId);
     void unfollowGroup(String userName,Long groupId);








}
