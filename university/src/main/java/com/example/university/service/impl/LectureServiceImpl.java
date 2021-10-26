package com.example.university.service.impl;

import com.example.university.model.Lecture;
import com.example.university.repository.LectureRepository;
import com.example.university.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepo;

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

        if (lectureData.getTitleLecture() != null) {
            lecture.setTitleLecture(lectureData.getTitleLecture());
        }

        if (lectureData.getAudience() != null) {
            lecture.setAudience(lectureData.getAudience());
        }

        if (lectureData.getGroup() != null) {
            lecture.setGroup(lectureData.getGroup());
        }

        if (lectureData.getDay() != null) {
            lecture.setDay(lectureData.getDay());
        }

        return lectureRepo.save(lecture);
    }
}
