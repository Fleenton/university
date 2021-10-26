package com.example.university.service.impl;

import com.example.university.constant.Days;
import com.example.university.model.Lecture;
import com.example.university.model.Student;
import com.example.university.repository.LectureRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final LectureRepository lectureRepo;

    private final StudentRepository studentRepo;

    @Override
    public List<Lecture> findTimetable(Long studentId, Days day) {
        Student student = studentRepo.getById(studentId);
        return lectureRepo.findTimetable(student.getStudentGroup().getGroupId(), day);
    }
}
