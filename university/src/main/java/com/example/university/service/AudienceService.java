package com.example.university.service;

import com.example.university.model.Audience;

import java.util.List;

public interface AudienceService {

    Audience getById(Long id);

    void save(Audience audience);

    void delete(Long id);

    List<Audience> getAll();

    Audience update(Long audienceId, Audience audience);
}
