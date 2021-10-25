package com.example.university.service.impl;

import com.example.university.constant.Days;
import com.example.university.model.Audience;
import com.example.university.model.Group;
import com.example.university.model.Lecture;
import com.example.university.repository.AudienceRepository;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.LectureRepository;
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
class LectureServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LectureRepository lectureRepo;

    @Test
    void getById() {
        Lecture lecture = lectureRepo.getById(1L);

        assertThat(lecture.getLectureId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(1L);
        lecture.setTitleLecture("Biology");
        lecture.setDay(Days.SUNDAY);
        lecture.getGroup().setGroupId(1L);
        lecture.getAudience().setAudienceId(2L);

        Lecture saveLecture = lectureRepo.save(lecture);

        Lecture existLecture = entityManager.find(Lecture.class, saveLecture.getLectureId());

        assertThat(lecture.getTitleLecture()).isEqualTo(existLecture.getTitleLecture());
    }

    @Test
    void delete() {
        Lecture lecture = lectureRepo.getById(1L);

        lectureRepo.delete(lecture);

        Lecture lecture1 = null;

        Optional<Lecture> optionalLecture = lectureRepo.findById(1L);

        if (optionalLecture.isPresent()) {
            lecture1 = optionalLecture.get();
        }

        assertThat(lecture1).isNull();
    }

    @Test
    void getAll() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(1L);
        lecture.setTitleLecture("Biology");
        lecture.setDay(Days.SUNDAY);
        lecture.getGroup().setGroupId(1L);
        lecture.getAudience().setAudienceId(2L);

        lectureRepo.save(lecture);

        List<Lecture> lectureList = lectureRepo.findAll();

        assertThat(lectureList.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(1L);
        lecture.setTitleLecture("Biology");
        lecture.setDay(Days.SUNDAY);
        lecture.getGroup().setGroupId(1L);
        lecture.getAudience().setAudienceId(2L);

        lectureRepo.save(lecture);

        lecture = lectureRepo.getById(1L);

        lecture.setTitleLecture("Physics");

        Lecture lectureUpdated = lectureRepo.save(lecture);

        assertThat(lectureUpdated.getTitleLecture()).isEqualTo("Physics");
    }

    @Test
    void findTimetable() {

    }
}