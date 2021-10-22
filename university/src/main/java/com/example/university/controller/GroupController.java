package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") Long groupId) {
        if (groupId == null) {
            return ResponseEntity.badRequest().build();
        }

        Group group = this.groupService.getById(groupId);

        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(group);
    }

    @PostMapping("")
    public ResponseEntity<Group> saveGroup(@RequestBody Group group) {

        if (group == null) {
            return ResponseEntity.badRequest().build();
        }

        this.groupService.save(group);
        return ResponseEntity.ok(group);
    }

    @PutMapping("{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long groupId, @RequestBody Group group) {

        if (groupId == null || group == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(groupService.update(groupId, group));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Long id) {
        Group group = this.groupService.getById(id);

        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        this.groupService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Group>> getAllGroup() {
        List<Group> groupList = this.groupService.getAll();

        if (groupList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(groupList);
    }
}
