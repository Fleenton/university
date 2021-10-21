package com.example.university.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "audience")
@Getter
@Setter
@ToString
public class Audience {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audienceId;

    @Column(name = "audience_number")
    private int audienceNumber;

    @OneToMany(mappedBy = "audience")
    private Set<Lecture> lectures = new HashSet<>();
}
