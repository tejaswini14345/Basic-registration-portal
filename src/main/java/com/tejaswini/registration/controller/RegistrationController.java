package com.tejaswini.registration.controller;

import com.tejaswini.registration.dto.RegistrationRequest;
import com.tejaswini.registration.model.Registration;
import com.tejaswini.registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Registration> register(@Valid @RequestBody RegistrationRequest request) {
        Registration created = service.register(request);
        return ResponseEntity
                .created(URI.create("/api/registrations/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Registration>> list() {
        return ResponseEntity.ok(service.getAll());
    }
}
