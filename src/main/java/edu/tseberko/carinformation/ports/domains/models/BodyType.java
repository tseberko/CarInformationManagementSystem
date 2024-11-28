package edu.tseberko.carinformation.ports.domains.models;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String note;
    private String description;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "id")
    private BodyType parent;

    public BodyType(String name, String note, String description, BodyType parent) {
        this.name = name;
        this.note = note;
        this.description = description;
        this.parent = parent;
    }

    public BodyType(String name, String note, String description) {
        this.name = name;
        this.note = note;
        this.description = description;
    }

    public BodyType() {
    }

    public void update(BodyTypePropertiesForUpdate properties) {
        this.name = properties.name();
        this.note = properties.note();
        this.description = properties.description();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public Long getParentId() {
        return this.parent == null ? null : this.parent.id;
    }

    public String getParentName() {
        return this.parent == null ? null : this.parent.name;
    }

    public void changeParent(BodyType parent) {
        this.parent = parent;
    }

    public boolean parentIdEquals(long parentId) {
        return this.parent != null && parent.id == parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return Objects.equals(id, bodyType.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
