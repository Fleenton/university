package com.example.university.controller;

import com.example.university.constant.Days;
import com.example.university.model.Group;
import com.example.university.model.Lecture;
import com.example.university.service.LectureService;
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
    public ResponseEntity<Lecture> getLecture(@PathVariable("id") Long lectureId) {
        if (lectureId == null) {
            return ResponseEntity.badRequest().build();
        }

        Lecture lecture = this.lectureService.getById(lectureId);

        if (lecture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lecture);
    }

    @PostMapping("")
    public ResponseEntity<Lecture> saveLecture(@RequestBody Lecture lecture) {

        if (lecture == null) {
            return ResponseEntity.badRequest().build();
        }

        this.lectureService.save(lecture);
        return ResponseEntity.ok(lecture);
    }

    @PutMapping("{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("id") Long lectureId, @RequestBody Lecture lecture) {

        if (lectureId == null || lecture == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(lectureService.update(lectureId, lecture));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> deleteLecture(@PathVariable("id") Long id) {
        Lecture lecture = this.lectureService.getById(id);

        if (lecture == null) {
            return ResponseEntity.notFound().build();
        }

        this.lectureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Lecture>> getAllLecture() {
        List<Lecture> lectureList = this.lectureService.getAll();

        if (lectureList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lectureList);
    }

    @GetMapping("timetable/{id}/{day}")
    public ResponseEntity<List<Lecture>> getTimetable(@PathVariable("id") Long studentId,
                                                      @PathVariable("day") Days day) {
        List<Lecture> timetable = this.lectureService.findTimetable(studentId, day);

        return ResponseEntity.ok(timetable);
    }
}
