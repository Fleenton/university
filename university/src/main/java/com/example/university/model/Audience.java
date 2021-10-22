package com.example.university.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "audience")
public class Audience {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audienceId;

    @Column(name = "audience_number")
    private int audienceNumber;

    @OneToMany(mappedBy = "audience", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lecture> lectures = new ArrayList<>();
}
