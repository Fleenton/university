package com.example.university.model;

import com.example.university.constant.Days;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lectures")
public class Lecture {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lectures_id_seq")
    @SequenceGenerator(name = "lectures_id_seq", sequenceName = "lectures_id_seq", initialValue = 100, allocationSize = 1)
    private Long lectureId;

    @Column(name = "title_lecture")
    private String titleLecture;

    @Column(name = "lecture_day")
    @Enumerated(EnumType.STRING)
    private Days day;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    private Audience audience;
}
