package edu.tseberko.carinformation.ports.domains.models;

public record BodyTypeIdName(long id, String name) {
    public BodyTypeIdName(BodyType bodyType) {
        this(bodyType.getId(), bodyType.getName());
    }
}
