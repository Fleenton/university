package com.example.university.service.impl;

import com.example.university.model.Student;
import com.example.university.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class StudentServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepo;

    @Test
    void getById() {
        Student student = studentRepo.getById(1L);

        assertThat(student.getStudentId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        student.setCourse(3);
        student.getStudentGroup().setGroupId(1L);

        Student saveStudent = studentRepo.save(student);

        Student existStudent = entityManager.find(Student.class, saveStudent.getStudentId());

        assertThat(student.getStudentId()).isEqualTo(existStudent.getStudentId());
    }

    @Test
    void delete() {
        Student student = studentRepo.getById(1L);

        studentRepo.delete(student);

        Student student1 = null;

        Optional<Student> optionalStudent = studentRepo.findById(1L);

        if (optionalStudent.isPresent()) {
            student1 = optionalStudent.get();
        }

        assertThat(student1).isNull();
    }

    @Test
    void getAll() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        student.setCourse(3);
        student.getStudentGroup().setGroupId(1L);

        studentRepo.save(student);

        List<Student> studentList = studentRepo.findAll();

        assertThat(studentList.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        student.setCourse(3);
        student.getStudentGroup().setGroupId(1L);

        studentRepo.save(student);

        student = studentRepo.getById(1L);

        student.setCourse(2);

        Student studentUpdated = studentRepo.save(student);

        assertThat(studentUpdated.getCourse()).isEqualTo(2);
    }
}