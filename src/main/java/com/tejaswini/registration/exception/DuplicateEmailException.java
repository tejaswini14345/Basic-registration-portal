package com.tejaswini.registration.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("A registration already exists for " + email);
    }
}
