package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalActionException extends RuntimeException {
    IllegalActionException(String message) {
        super(message);
    }

    public static IllegalActionException recordReferencedByAnotherRecords() {
        return new IllegalActionException("cannot delete a record referenced by another records");
    }

    public static IllegalActionException bodyTypeReferencedByModelRecords() {
        return new IllegalActionException("cannot delete a body type record as it is referenced by model records");
    }

    public static IllegalActionException bodyTypeAlreadyAssigned(long id, long parentId) {
        return new IllegalActionException("BodyType with id " + id + " already assigned to parent with id " + parentId);
    }

//    public static IllegalActionException exception() {
//        return new IllegalActionException("BodyType with id " + id + " already assigned to parent with id " + parentId);
//    }


}
