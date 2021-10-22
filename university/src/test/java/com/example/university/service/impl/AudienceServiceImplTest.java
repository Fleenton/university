package com.example.university.service.impl;

import com.example.university.model.Audience;
import com.example.university.repository.AudienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class AudienceServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AudienceRepository audienceRepo;

    @Test
    void getById() {
        Audience audience = audienceRepo.getById(1L);

        assertThat(audience.getAudienceId()).isEqualTo(1L);
    }

    @Test
    void save() {
        Audience audience = new Audience();
        audience.setAudienceId(1L);
        audience.setAudienceNumber(95);

        Audience saveAudience = audienceRepo.save(audience);

        Audience existAudience = entityManager.find(Audience.class, saveAudience.getAudienceId());

        assertThat(audience.getAudienceNumber()).isEqualTo(existAudience.getAudienceNumber());
    }

    @Test
    void delete() {
        Audience audience = audienceRepo.getById(1L);

        audienceRepo.delete(audience);

        Audience audience1 = null;

        Optional<Audience> optionalAudience = audienceRepo.findById(1L);

        if (optionalAudience.isPresent()) {
            audience1 = optionalAudience.get();
        }

        assertThat(audience1).isNull();
    }

    @Test
    void getAll() {
        Audience audience = new Audience();
        audience.setAudienceId(1L);
        audience.setAudienceNumber(95);
        audienceRepo.save(audience);

        List<Audience> audienceList = audienceRepo.findAll();

        assertThat(audienceList.size()).isGreaterThan(0);
    }

    @Test
    void update() {
        Audience audience = new Audience();
        audience.setAudienceId(1L);
        audience.setAudienceNumber(95);
        audienceRepo.save(audience);

        audience = audienceRepo.getById(1L);

        audience.setAudienceNumber(60);

        Audience audienceUpdated = audienceRepo.save(audience);

        assertThat(audienceUpdated.getAudienceNumber()).isEqualTo(60);
    }
}