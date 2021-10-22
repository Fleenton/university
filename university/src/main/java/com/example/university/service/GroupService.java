package com.example.university.service;

import com.example.university.model.Group;

import java.util.List;

public interface GroupService {

    Group getById(Long id);

    void save(Group group);

    void delete(Long id);

    List<Group> getAll();

    Group update(Long groupId, Group group);
}
