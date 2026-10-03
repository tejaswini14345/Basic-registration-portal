package com.tejaswini.registration.service;

import com.tejaswini.registration.dto.RegistrationRequest;
import com.tejaswini.registration.exception.DuplicateEmailException;
import com.tejaswini.registration.model.Registration;
import com.tejaswini.registration.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository repository;

    public RegistrationService(RegistrationRepository repository) {
        this.repository = repository;
    }

    public Registration register(RegistrationRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        if (repository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new DuplicateEmailException(normalizedEmail);
        }

        Registration registration = new Registration();
        registration.setFirstName(request.firstName().trim());
        registration.setLastName(request.lastName().trim());
        registration.setEmail(normalizedEmail);
        registration.setPhone(request.phone() == null ? "" : request.phone().trim());
        registration.setProgram(request.program().trim());

        return repository.save(registration);
    }

    public List<Registration> getAll() {
        return repository.findAll();
    }
}
