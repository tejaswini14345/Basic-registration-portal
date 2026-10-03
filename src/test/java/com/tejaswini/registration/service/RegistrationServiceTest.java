package com.tejaswini.registration.service;

import com.tejaswini.registration.dto.RegistrationRequest;
import com.tejaswini.registration.exception.DuplicateEmailException;
import com.tejaswini.registration.model.Registration;
import com.tejaswini.registration.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private RegistrationRepository repository;

    private RegistrationService service;

    @BeforeEach
    void setUp() {
        service = new RegistrationService(repository);
    }

    @Test
    void registerNormalizesAndSavesRegistration() {
        RegistrationRequest request = new RegistrationRequest(
                " Tejaswini ",
                " Betina ",
                "TEST@EXAMPLE.COM",
                "313-555-0100",
                "Software Engineering");

        when(repository.existsByEmailIgnoreCase("test@example.com")).thenReturn(false);
        when(repository.save(any(Registration.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Registration saved = service.register(request);

        assertEquals("Tejaswini", saved.getFirstName());
        assertEquals("Betina", saved.getLastName());
        assertEquals("test@example.com", saved.getEmail());
        verify(repository).save(any(Registration.class));
    }

    @Test
    void registerRejectsDuplicateEmail() {
        when(repository.existsByEmailIgnoreCase("test@example.com")).thenReturn(true);

        RegistrationRequest request = new RegistrationRequest(
                "Tejaswini", "Betina", "test@example.com", "", "Data & AI");

        assertThrows(DuplicateEmailException.class, () -> service.register(request));
        verify(repository, never()).save(any());
    }
}
