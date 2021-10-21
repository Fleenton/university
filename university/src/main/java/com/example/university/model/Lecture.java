package com.example.university.model;

import com.example.university.constant.Days;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "lectures")
@Getter
@Setter
@ToString
public class Lecture {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(name = "title_lecture")
    private String titleLecture;

    @Column(name = "lecture_day")
    @Enumerated(EnumType.STRING)
    private Days days;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    private Audience audience;
}
