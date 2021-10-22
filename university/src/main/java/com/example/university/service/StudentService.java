package com.example.university.service;

import com.example.university.model.Student;

import java.util.List;

public interface StudentService {

    Student getById(Long id);

    void save(Student student);

    void delete(Long id);

    List<Student> getAll();

    Student update(Long studentId, Student student);
}
