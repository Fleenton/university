package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.model.Student;
import com.example.university.repository.LectureRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {StudentServiceImpl.class})
class StudentServiceImplTest {

    @MockBean
    private StudentRepository studentRepo;

    @MockBean
    private LectureRepository lectureRepository;

    @Autowired
    private StudentService studentService;

    @Test
    void save() {
        Student student = getStudent();
        studentService.save(student);

        assertThat(student.getStudentId()).isGreaterThan(0);
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
    void getById() {
        when(studentRepo.findById(1L))
                .thenReturn(Optional.of(getStudent()));

        assertThat(studentService.getById(1L).getStudentId()).isEqualTo(1L);
    }

    @Test
    void getAll() {
        when(studentRepo.findAll())
                .thenReturn(getStudentList());

        List<Student> all = studentService.getAll();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals("Ivan", all.get(0).getFirstName());
    }

    @Test
    void update() {
        when(studentRepo.findById(1L))
                .thenReturn(Optional.of(getStudent()));

        when(studentRepo.save(any(Student.class))).then(returnsFirstArg());

        Student student = getStudent();
        Student studentData = getStudentData();

        Student updatedStudent = studentService.update(1L, studentData);

        assertNotNull(updatedStudent);
        assertEquals(updatedStudent.getStudentId(), student.getStudentId());
        assertNotEquals(updatedStudent.getFirstName(), student.getFirstName());
    }

    Student getStudent() {
        return Student.builder()
                .studentId(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .course(1)
                .studentGroup(Group.builder()
                        .groupId(1L)
                        .groupNumber(101)
                        .build())
                .build();
    }

    Student getStudentData() {
        return Student.builder()
                .firstName("Denis")
                .lastName("Ivanov")
                .course(1)
                .studentGroup(Group.builder()
                        .groupId(1L)
                        .groupNumber(101)
                        .build())
                .build();
    }

    private List<Student> getStudentList() {
        return List.of(Student.builder()
                        .studentId(1L)
                        .firstName("Ivan")
                        .lastName("Ivanov")
                        .course(1)
                        .studentGroup(Group.builder()
                                .groupId(1L)
                                .groupNumber(101)
                                .build())
                        .build(),
                Student.builder()
                        .studentId(2L)
                        .firstName("John")
                        .lastName("Smith")
                        .course(2)
                        .studentGroup(Group.builder()
                                .groupId(2L)
                                .groupNumber(102)
                                .build())
                        .build(),
                Student.builder()
                        .studentId(3L)
                        .firstName("Anna")
                        .lastName("Lee")
                        .course(3)
                        .studentGroup(Group.builder()
                                .groupId(3L)
                                .groupNumber(103)
                                .build())
                        .build());
    }
}