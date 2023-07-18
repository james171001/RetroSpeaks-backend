package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.model.Category;
import com.champ.retrospeaks.model.Group;
import com.champ.retrospeaks.repository.CategoryRepository;
import com.champ.retrospeaks.repository.GroupRepository;
import com.champ.retrospeaks.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private GroupServiceImpl groupService;
    private Group group;

    @BeforeEach
    public void setUp(){
        groupService = new GroupServiceImpl(groupRepository, userRepository, categoryRepository);
        group = Group.builder()
                .id(1L)
                .name("Batman")
                .description("Batman is the best")
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .groupOwner(1L)
                .users(Collections.emptyList())
                .category(new Category())
                .build();
    }

    @Test
    void getById(){
        Long groupId = 1L;
        when(groupRepository.getReferenceById(groupId)).thenReturn(group);

        GroupDto result = groupService.getById(groupId);

        assertEquals(groupId, result.getId());

    }
    @Test
    void getById_NonexistentGroupId_ThrowsException() {

        Long groupId = 2L;
        when(groupRepository.getReferenceById(groupId)).thenThrow(new IllegalArgumentException("Group does not exist"));

        assertThrows(IllegalArgumentException.class, () -> groupService.getById(groupId));

        verify(groupRepository, times(1)).getReferenceById(groupId);
    }

    @Test
    void findAll(){
        List<Group> groupList = new ArrayList<>();
        List<GroupDto> groupDtoList = groupService.findAll();
        groupList.add(group);
        when(groupRepository.findAll()).thenReturn(groupList);
        groupDtoList = groupService.findAll();
        assertEquals(groupList.size(),groupDtoList.size());
    }
    @Test
    void findAll_ReturnEmptyList(){
        List<Group> groupList = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(Collections.emptyList());
        List<GroupDto> groupDtoList = groupService.findAll();
        assertEquals(0,groupDtoList.size());
    }

    @Test
    void isGroupExist(){

        Boolean expected = true;
        when(groupRepository.existsById(1L)).thenReturn(true);

        Boolean actual = groupService.isGroupExist(1L);

        assertEquals(expected,actual);
    }
}