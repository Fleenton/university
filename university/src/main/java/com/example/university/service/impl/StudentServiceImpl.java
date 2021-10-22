package com.example.university.service.impl;

import com.example.university.model.Student;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    @Override
    public Student getById(Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student update(Long studentId, Student studentData) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        student.setStudentId(studentData.getStudentId());
        return studentRepo.save(student);
    }
}
