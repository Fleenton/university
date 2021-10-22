package com.example.university.repository;

import com.example.university.constant.Days;
import com.example.university.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("SELECT l FROM Lecture l WHERE l.group = :groupId and l.days = :day")
    List<Lecture> findTimetable(Long groupId, Days day);
}
