package com.example.university.service.impl;

import com.example.university.model.Audience;
import com.example.university.repository.AudienceRepository;
import com.example.university.service.AudienceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AudienceServiceImpl.class})
class AudienceServiceImplTest {

    @MockBean
    private AudienceRepository audienceRepo;

    @Autowired
    private AudienceService audienceService;

    @Test
    void getById() {
        when(audienceRepo.findById(1L)).thenReturn(Optional.of(getAudience()));

        assertThat(audienceService.getById(1L).getAudienceId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Audience audience = getAudience();
        audienceService.save(audience);

        assertThat(audience.getAudienceId()).isGreaterThan(0);
    }

    @Test
    void delete() {
        Audience audience = audienceRepo.getById(1L);

        audienceRepo.delete(audience);

        Audience audienceDel = null;

        Optional<Audience> optionalAudience = audienceRepo.findById(1L);

        if (optionalAudience.isPresent()) {
            audienceDel = optionalAudience.get();
        }

        assertThat(audienceDel).isNull();
    }

    @Test
    void getAll() {
        when(audienceRepo.findAll()).thenReturn(getAudienceList());

        List<Audience> all = audienceService.getAll();

        assertFalse(all.isEmpty());
        assertEquals(3, all.size());
        assertEquals(103, all.get(2).getAudienceNumber());
    }

    @Test
    void update() {
        when(audienceRepo.findById(1L)).thenReturn(Optional.of(getAudience()));
        when(audienceRepo.save(any(Audience.class))).then(returnsFirstArg());

        Audience audience = getAudience();
        Audience audienceData = getAudienceData();

        Audience updatedAudience = audienceService.update(1L, audienceData);

        assertNotNull(updatedAudience);
        assertEquals(updatedAudience.getAudienceId(), audience.getAudienceId());
        assertNotEquals(updatedAudience.getAudienceNumber(), audience.getAudienceNumber());
    }

    Audience getAudience() {
        return Audience.builder()
                .audienceId(1L)
                .audienceNumber(100)
                .build();
    }

    Audience getAudienceData() {
        return Audience.builder()
                .audienceNumber(200)
                .build();
    }

    private List<Audience> getAudienceList() {
        return List.of(Audience.builder()
                        .audienceId(1L)
                        .audienceNumber(101)
                        .build(),
                Audience.builder()
                        .audienceId(2L)
                        .audienceNumber(102)
                        .build(),
                Audience.builder()
                        .audienceId(3L)
                        .audienceNumber(103)
                        .build());
    }
}
