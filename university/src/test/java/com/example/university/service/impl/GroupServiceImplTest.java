package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class GroupServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroupRepository groupRepo;

    @Test
    void getById() {
        Group group = groupRepo.getById(1L);

        assertThat(group.getGroupId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupNumber(358);

        Group saveGroup = groupRepo.save(group);

        Group existGroup = entityManager.find(Group.class, saveGroup.getGroupId());

        assertThat(group.getGroupNumber()).isEqualTo(existGroup.getGroupNumber());
    }

    @Test
    void delete() {
        Group group = groupRepo.getById(1L);

        groupRepo.delete(group);

        Group group1 = null;

        Optional<Group> optionalGroup = groupRepo.findById(1L);

        if (optionalGroup.isPresent()) {
            group1 = optionalGroup.get();
        }

        assertThat(group1).isNull();
    }

    @Test
    void getAll() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupNumber(358);

        groupRepo.save(group);

        List<Group> groupList = groupRepo.findAll();

        assertThat(groupList.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupNumber(358);

        groupRepo.save(group);

        group = groupRepo.getById(1L);

        group.setGroupNumber(211);

        Group groupUpdated = groupRepo.save(group);

        assertThat(groupUpdated.getGroupNumber()).isEqualTo(211);
    }
}