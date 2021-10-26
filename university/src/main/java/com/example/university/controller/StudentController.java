package com.example.university.controller;

import com.example.university.model.Student;
import com.example.university.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("{id}")
    @ApiOperation("Get student by id.")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long studentId) {
        if (studentId == null) {
            return ResponseEntity.badRequest().build();
        }

        Student student = studentService.getById(studentId);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @PostMapping("")
    @ApiOperation("Save student.")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {

        if (student == null) {
            return ResponseEntity.badRequest().build();
        }

        studentService.save(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    @ApiOperation("Update student by id.")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId, @RequestBody Student student) {

        if (studentId == null || student == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(studentService.update(studentId, student));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete student by id.")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    @ApiOperation("Get all students.")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAll();

        if (studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);
    }
}
