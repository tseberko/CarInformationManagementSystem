package edu.tseberko.carinformation.adapters.mvc.controllers.body_type;

import edu.tseberko.carinformation.ports.domains.models.BodyTypePropertiesForUpdate;
import edu.tseberko.carinformation.ports.domains.models.BodyTypeView;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class BodyTypeUpdate implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private String note;
    private String description;

    public BodyTypePropertiesForUpdate toUpdateProperties() {
        return new BodyTypePropertiesForUpdate(this.name, this.note, this.description);
    }

    public BodyTypeUpdate init(BodyTypeView bodyTypeView) {
        this.id = bodyTypeView.id();
        this.name = bodyTypeView.name();
        this.note = bodyTypeView.note();
        this.description = bodyTypeView.description();
        return this;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyTypeUpdate that = (BodyTypeUpdate) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!Objects.equals(note, that.note)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
