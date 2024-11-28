package edu.tseberko.carinformation.ports.domains.models;

public record BodyTypeView(long id, String name, String note, String description, Long parentId, String parentName) {
    public BodyTypeView(BodyType saved) {
        this(saved.getId(), saved.getName(), saved.getNote(), saved.getDescription(), saved.getParentId(), saved.getParentName());
    }

    public BodyTypeIdName reduceToIdAndName() {
        return new BodyTypeIdName(id, name);
    }

    public boolean parentIdIsNot(Long parentId) {
        return this.parentId != parentId;
    }

    public boolean idIsNot(long id) {
        return this.id != id;
    }

}
