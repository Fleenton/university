package com.example.university.service.impl;

import com.example.university.constant.Days;
import com.example.university.model.Audience;
import com.example.university.model.Group;
import com.example.university.model.Lecture;
import com.example.university.model.Student;
import com.example.university.repository.LectureRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.service.TimetableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {TimetableServiceImpl.class})
class TimetableServiceImplTest {

    @MockBean
    private LectureRepository lectureRepo;

    @MockBean
    private StudentRepository studentRepo;

    @Autowired
    private TimetableService timetableService;

    @Test
    void findTimetable() {
        when(studentRepo.getById(anyLong()))
                .thenReturn(Student.builder()
                        .studentId(1L)
                        .course(1)
                        .studentGroup(Group.builder()
                                .groupId(1L)
                                .groupNumber(100)
                                .build())
                        .build());
        when(lectureRepo.getById(anyLong()))
                .thenReturn(Lecture.builder()
                        .lectureId(1L)
                        .titleLecture("Biology")
                        .day(Days.MONDAY)
                        .group(Group.builder()
                                .groupId(1L)
                                .groupNumber(100)
                                .build())
                        .audience(Audience.builder()
                                .audienceId(1L)
                                .audienceNumber(1)
                                .build())
                        .build());
        when(lectureRepo.findTimetable(anyLong(), any(Days.class))).thenReturn(getLectureList());

        Student student = studentRepo.getById(1L);
        Lecture lecture = lectureRepo.getById(1L);

        List<Lecture> timetable = timetableService.findTimetable(student.getStudentGroup().getGroupId(), lecture.getDay());

        assertFalse(timetable.isEmpty());
        assertEquals(Days.MONDAY, timetable.get(0).getDay());
    }

    private List<Lecture> getLectureList() {
        return List.of(Lecture.builder()
                        .lectureId(1L)
                        .titleLecture("Biology")
                        .day(Days.MONDAY)
                        .group(Group.builder()
                                .groupId(1L)
                                .groupNumber(102)
                                .build())
                        .audience(Audience.builder()
                                .audienceId(1L)
                                .audienceNumber(1)
                                .build())
                        .build(),
                Lecture.builder()
                        .lectureId(2L)
                        .titleLecture("Mathematics")
                        .day(Days.MONDAY)
                        .group(Group.builder()
                                .groupId(2L)
                                .groupNumber(103)
                                .build())
                        .audience(Audience.builder()
                                .audienceId(2L)
                                .audienceNumber(2)
                                .build())
                        .build(),
                Lecture.builder()
                        .lectureId(3L)
                        .titleLecture("Chemistry")
                        .day(Days.MONDAY)
                        .group(Group.builder()
                                .groupId(3L)
                                .groupNumber(104)
                                .build())
                        .audience(Audience.builder()
                                .audienceId(3L)
                                .audienceNumber(3)
                                .build())
                        .build());
    }
}