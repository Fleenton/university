package com.example.university.service.impl;

import com.example.university.constant.Days;
import com.example.university.model.Lecture;
import com.example.university.model.Student;
import com.example.university.repository.LectureRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepo;

    private final StudentRepository studentRepo;

    @Override
    public Lecture getById(Long id) {
        return lectureRepo.findById(id).orElseThrow();
    }

    @Override
    public void save(Lecture lecture) {
        lectureRepo.save(lecture);
    }

    @Override
    public void delete(Long id) {
        lectureRepo.deleteById(id);
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepo.findAll();
    }

    @Override
    public Lecture update(Long lectureId, Lecture lectureData) {
        Lecture lecture = lectureRepo.findById(lectureId).orElseThrow();
        lecture.setLectureId(lectureData.getLectureId());
        return lectureRepo.save(lecture);
    }

    @Override
    public List<Lecture> findTimetable(Long studentId, Days day) {
        Student student = studentRepo.getById(studentId);
        return lectureRepo.findTimetable(student.getStudentGroup().getGroupId(), day);
    }
}
