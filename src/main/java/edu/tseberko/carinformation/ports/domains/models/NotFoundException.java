package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Entity with id " + id + " does not exists!");
    }
}

