package com.example.university.service;

import com.example.university.model.Lecture;

import java.util.List;

public interface LectureService {

    Lecture getById(Long id);

    void save(Lecture lecture);

    void delete(Long id);

    List<Lecture> getAll();

    Lecture update(Long lectureId, Lecture lecture);
}
