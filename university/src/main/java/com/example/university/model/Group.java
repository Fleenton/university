package com.example.university.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_groups")
@Data
public class Group {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name = "group_number", nullable = false)
    private int groupNumber;

    @OneToMany(mappedBy = "studentGroup")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Lecture> lectures = new HashSet<>();
}
