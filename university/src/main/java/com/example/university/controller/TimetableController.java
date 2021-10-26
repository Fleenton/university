package com.example.university.controller;

import com.example.university.constant.Days;
import com.example.university.model.Lecture;
import com.example.university.service.TimetableService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timetable/")
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("{id}/{day}")
    @ApiOperation("Getting a schedule for a student.")
    public ResponseEntity<List<Lecture>> getTimetable(@PathVariable("id") Long studentId,
                                                      @PathVariable("day") Days day) {
        List<Lecture> timetable = this.timetableService.findTimetable(studentId, day);

        return ResponseEntity.ok(timetable);
    }
}
