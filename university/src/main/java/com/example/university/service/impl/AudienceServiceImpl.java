package com.example.university.service.impl;

import com.example.university.model.Audience;
import com.example.university.repository.AudienceRepository;
import com.example.university.service.AudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudienceServiceImpl implements AudienceService {

    private final AudienceRepository audienceRepo;

    @Override
    public Audience getById(Long id) {
        return audienceRepo.findById(id).orElseThrow();
    }

    @Override
    public void save(Audience audience) {
        audienceRepo.save(audience);
    }

    @Override
    public void delete(Long id) {
        audienceRepo.deleteById(id);
    }

    @Override
    public List<Audience> getAll() {
        return audienceRepo.findAll();
    }

    @Override
    public Audience update(Long audienceId, Audience audienceData) {
        Audience audience = audienceRepo.findById(audienceId).orElseThrow();

        if (audienceData.getAudienceNumber() != null) {
            audience.setAudienceNumber(audienceData.getAudienceNumber());
        }

        return audienceRepo.save(audience);
    }
}
