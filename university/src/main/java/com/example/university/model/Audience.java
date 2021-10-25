package com.example.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audience")
public class Audience {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audience_id_seq")
    @SequenceGenerator(name = "audience_id_seq", sequenceName = "audience_id_seq", initialValue = 100, allocationSize = 1)
    private Long audienceId;

    @Column(name = "audience_number")
    private Integer audienceNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "audience", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures = new ArrayList<>();
}
