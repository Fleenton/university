package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.model.Lecture;
import com.example.university.service.LectureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture/")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("{id}")
    @ApiOperation("Get lecture by id.")
    public ResponseEntity<Lecture> getLecture(@PathVariable("id") Long lectureId) {
        if (lectureId == null) {
            return ResponseEntity.badRequest().build();
        }

        Lecture lecture = lectureService.getById(lectureId);

        if (lecture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lecture);
    }

    @PostMapping("")
    @ApiOperation("Save lecture.")
    public ResponseEntity<Lecture> saveLecture(@RequestBody Lecture lecture) {

        if (lecture == null) {
            return ResponseEntity.badRequest().build();
        }

        lectureService.save(lecture);
        return ResponseEntity.ok(lecture);
    }

    @PutMapping("{id}")
    @ApiOperation("Update lecture by id.")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("id") Long lectureId, @RequestBody Lecture lecture) {

        if (lectureId == null || lecture == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(lectureService.update(lectureId, lecture));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete lecture by id.")
    public ResponseEntity<Group> deleteLecture(@PathVariable("id") Long id) {
        Lecture lecture = lectureService.getById(id);

        if (lecture == null) {
            return ResponseEntity.notFound().build();
        }

        lectureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    @ApiOperation("Get all lectures.")
    public ResponseEntity<List<Lecture>> getAllLecture() {
        List<Lecture> lectureList = lectureService.getAll();

        if (lectureList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lectureList);
    }
}
