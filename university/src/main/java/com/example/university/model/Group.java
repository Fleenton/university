package com.example.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "student_groups")
public class Group {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_groups_id_seq")
    @SequenceGenerator(name = "student_groups_id_seq", sequenceName = "student_groups_id_seq",
            initialValue = 100, allocationSize = 1)
    private Long groupId;

    @Column(name = "group_number", nullable = false)
    private Integer groupNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures = new ArrayList<>();
}
