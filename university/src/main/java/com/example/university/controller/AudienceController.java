package com.example.university.controller;

import com.example.university.model.Audience;
import com.example.university.service.AudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/audience/")
public class AudienceController {

    private final AudienceService audienceService;

    @GetMapping("{id}")
    public ResponseEntity<Audience> getAudience(@PathVariable("id") Long audienceId) {
        if (audienceId == null) {
            return ResponseEntity.badRequest().build();
        }

        Audience audience = this.audienceService.getById(audienceId);

        if (audience == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(audience);
    }

    @PostMapping("")
    public ResponseEntity<Audience> saveAudience(@RequestBody Audience audience) {

        if (audience == null) {
            return ResponseEntity.badRequest().build();
        }

        this.audienceService.save(audience);
        return ResponseEntity.ok(audience);
    }

    @PutMapping("{id}")
    public ResponseEntity<Audience> updateAudience(@PathVariable("id") Long audienceId, @RequestBody Audience audience) {

        if (audienceId == null || audience == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(audienceService.update(audienceId, audience));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Audience> deleteAudience(@PathVariable("id") Long id) {
        Audience audience = this.audienceService.getById(id);

        if (audience == null) {
            return ResponseEntity.notFound().build();
        }

        this.audienceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Audience>> getAllAudience() {
        List<Audience> audienceList = this.audienceService.getAll();

        if (audienceList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(audienceList);
    }
}
