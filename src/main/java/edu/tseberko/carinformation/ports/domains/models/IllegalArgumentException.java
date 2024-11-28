package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// не привльний http статус
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {

    private IllegalArgumentException(String messages) {
        super(messages);
    }

    public static IllegalArgumentException modelBodyTypeIdNotExists(long id) {
        return new IllegalArgumentException("Model with id of bodyType " + id + " does not exists!");
    }

    public static IllegalArgumentException parentBodyTypeIdNotExists(long bodyTypeId) {
        return new IllegalArgumentException("BodyType with id " + bodyTypeId + " does not exists!");

    }

    public static IllegalArgumentException parentIdNotFound(long parentId) {
        return new IllegalArgumentException("cannot resolve parent with id " + parentId);
    }
}
