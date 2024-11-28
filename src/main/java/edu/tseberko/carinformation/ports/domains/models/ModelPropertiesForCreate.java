package edu.tseberko.carinformation.ports.domains.models;

public record ModelPropertiesForCreate(String name, long bodyTypeId, short numOfSeats, float maxSpeed) {
    public Model createModel(BodyType bodyType) {
        return new Model(name, bodyType, numOfSeats, maxSpeed);
    }
}
