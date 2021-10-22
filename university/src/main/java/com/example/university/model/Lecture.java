package com.example.university.model;

import com.example.university.constant.Days;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lectures")
public class Lecture {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(name = "title_lecture")
    private String titleLecture;

    @Column(name = "lecture_day")
    @Enumerated(EnumType.STRING)
    private Days day;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    @JsonBackReference
    private Audience audience;
}
