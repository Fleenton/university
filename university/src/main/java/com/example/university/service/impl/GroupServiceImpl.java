package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.repository.GroupRepository;
import com.example.university.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepo;

    @Override
    public Group getById(Long id) {
        return groupRepo.findById(id).orElseThrow();
    }

    @Override
    public void save(Group group) {
        groupRepo.save(group);
    }

    @Override
    public void delete(Long id) {
        groupRepo.deleteById(id);
    }

    @Override
    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    @Override
    public Group update(Long groupId, Group groupData) {
        Group group = groupRepo.findById(groupId).orElseThrow();
        group.setGroupNumber(groupData.getGroupNumber());
        return groupRepo.save(group);
    }
}
