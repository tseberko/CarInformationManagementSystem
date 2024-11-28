package edu.tseberko.carinformation.adapters.mvc.controllers.body_type;

import edu.tseberko.carinformation.ports.domains.models.BodyTypePropertiesForCreate;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class BodyTypeCreate {
    @NotBlank
    private String name;
    private String note;
    private String description;
    private Long parentId;

    public BodyTypePropertiesForCreate toCreateProperties() {
        var parentIdValue = this.parentId == -1 ? null : parentId;
        return new BodyTypePropertiesForCreate(this.name, this.note, this.description, parentIdValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyTypeCreate that = (BodyTypeCreate) o;

        if (!name.equals(that.name)) return false;
        if (!Objects.equals(note, that.note)) return false;
        if (!Objects.equals(description, that.description)) return false;
        return Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }
}