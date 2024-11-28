package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalStateException extends RuntimeException{
    private IllegalStateException(String messages){
        super(messages);
    }

    public static IllegalStateException exception(long id, long bodyTypeId){
        return new IllegalStateException("Model with id " + id + " is already of bodyType with id " + bodyTypeId);
    }
}
