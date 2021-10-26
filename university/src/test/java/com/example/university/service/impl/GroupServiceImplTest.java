package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.repository.GroupRepository;
import com.example.university.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {GroupServiceImpl.class})
class GroupServiceImplTest {

    @MockBean
    private GroupRepository groupRepo;

    @Autowired
    private GroupService groupService;

    @Test
    void getById() {
        when(groupRepo.findById(1L)).thenReturn(Optional.of(getGroup()));

        assertThat(groupService.getById(1L).getGroupId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Group group = getGroup();
        groupService.save(group);

        assertThat(group.getGroupId()).isGreaterThan(0);
    }

    @Test
    void delete() {
        Group group = groupRepo.getById(1L);

        groupRepo.delete(group);

        Group groupDel = null;

        Optional<Group> optionalGroup = groupRepo.findById(1L);

        if (optionalGroup.isPresent()) {
            groupDel = optionalGroup.get();
        }

        assertThat(groupDel).isNull();
    }

    @Test
    void getAll() {
        when(groupRepo.findAll()).thenReturn(getGroupList());

        List<Group> all = groupService.getAll();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals(103, all.get(2).getGroupNumber());
    }

    @Test
    void update() {
        when(groupRepo.findById(1L)).thenReturn(Optional.of(getGroup()));
        when(groupRepo.save(any(Group.class))).then(returnsFirstArg());

        Group group = getGroup();
        Group groupData = getGroupData();

        Group updatedGroup = groupService.update(1L, groupData);

        assertNotNull(updatedGroup);
        assertEquals(updatedGroup.getGroupId(), group.getGroupId());
        assertNotEquals(updatedGroup.getGroupNumber(), group.getGroupNumber());
    }

    Group getGroup() {
        return Group.builder()
                .groupId(1L)
                .groupNumber(100)
                .build();
    }

    Group getGroupData() {
        return Group.builder()
                .groupNumber(200)
                .build();
    }

    private List<Group> getGroupList() {
        return List.of(Group.builder()
                        .groupId(1L)
                        .groupNumber(101)
                        .build(),
                Group.builder()
                        .groupId(2L)
                        .groupNumber(102)
                        .build(),
                Group.builder()
                        .groupId(3L)
                        .groupNumber(103)
                        .build());
    }
}