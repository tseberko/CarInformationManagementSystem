package edu.tseberko.carinformation.ports.domains.models;

public record ModelView(long id, String name, BodyTypeIdName bodyType, short numOfSeats, float maxSpeed) {

    public ModelView(Model model) {
        this(model.getId(), model.getName(), new BodyTypeIdName(model.getBodyType()), model.getNumberOfSeats(), model.getMaxSpeed());
    }

    public boolean bodyTypeIdNameIsNot(Long parentId) {
        return this.bodyType.id() != parentId;
    }

    public BodyTypeIdName reduceToIdAndName() {
        return new BodyTypeIdName(id, name);
    }
}
