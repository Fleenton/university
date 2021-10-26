package com.example.university.service;

import com.example.university.constant.Days;
import com.example.university.model.Lecture;

import java.util.List;

public interface TimetableService {
    List<Lecture> findTimetable(Long studentId, Days day);
}
