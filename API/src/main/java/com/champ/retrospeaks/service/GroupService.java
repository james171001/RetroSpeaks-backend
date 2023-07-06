package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;

import java.util.List;

public interface GroupService {

    GroupDto getById(Long id);

    List<GroupDto> findAll();

     void create (GroupForCreationDto groupForCreationDto);




}
