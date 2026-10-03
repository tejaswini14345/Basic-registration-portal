package com.tejaswini.registration.repository;

import com.tejaswini.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
