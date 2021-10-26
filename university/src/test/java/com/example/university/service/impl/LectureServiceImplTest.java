package com.example.university.service.impl;

import com.example.university.constant.Days;
import com.example.university.model.Audience;
import com.example.university.model.Group;
import com.example.university.model.Lecture;
import com.example.university.repository.LectureRepository;
import com.example.university.service.LectureService;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {LectureServiceImpl.class})
class LectureServiceImplTest {

    @MockBean
    private LectureRepository lectureRepo;

    @Autowired
    private LectureService lectureService;

    @Test
    void getById() {
        when(lectureRepo.findById(1L)).thenReturn(Optional.of(getLecture()));

        assertThat(lectureService.getById(1L).getLectureId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Lecture lecture = getLecture();
        lectureService.save(lecture);

        assertThat(lecture.getLectureId()).isGreaterThan(0);
    }

    @Test
    void delete() {
        Lecture lecture = lectureRepo.getById(1L);

        lectureRepo.delete(lecture);

        Lecture lectureDel = null;

        Optional<Lecture> optionalLecture = lectureRepo.findById(1L);

        if (optionalLecture.isPresent()) {
            lectureDel = optionalLecture.get();
        }

        assertThat(lectureDel).isNull();
    }

    @Test
    void getAll() {
        when(lectureRepo.findAll())
                .thenReturn(getLectureList());

        List<Lecture> all = lectureService.getAll();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals("Mathematics", all.get(1).getTitleLecture());
    }

    @Test
    void update() {
        when(lectureRepo.findById(1L)).thenReturn(Optional.of(getLecture()));
        when(lectureRepo.save(any(Lecture.class))).then(returnsFirstArg());

        Lecture lecture = getLecture();
        Lecture lectureData = getLectureData();

        Lecture updatedLecture = lectureService.update(1L, lectureData);

        assertNotNull(updatedLecture);
        assertEquals(updatedLecture.getLectureId(), lecture.getLectureId());
        assertNotEquals(updatedLecture.getTitleLecture(), lecture.getTitleLecture());
    }

    Lecture getLecture() {
        return Lecture.builder()
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
                .build();
    }

    Lecture getLectureData() {
        return Lecture.builder()
                .titleLecture("Chemistry")
                .day(Days.MONDAY)
                .group(Group.builder()
                        .groupId(1L)
                        .groupNumber(101)
                        .build())
                .audience(Audience.builder()
                        .audienceId(1L)
                        .audienceNumber(1)
                        .build())
                .build();
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