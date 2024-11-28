package edu.tseberko.carinformation.ports.domains.models;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "body_id")
    private BodyType bodyType;
    private short numberOfSeats;
    private float maxSpeed;

    public Model() {
    }

    public Model(String name, BodyType bodyType, short numberOfSeats, float maxSpeed) {
        this.name = name;
        this.bodyType = bodyType;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
    }

    public void update(ModelPropertiesForUpdate modelPropertiesForUpdate) {
        this.name = modelPropertiesForUpdate.name();
        this.numberOfSeats = modelPropertiesForUpdate.numOfSeats();
        this.maxSpeed = modelPropertiesForUpdate.maxSpeed();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public short getNumberOfSeats() {
        return numberOfSeats;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean bodyTypeIdEquals(long bodyTypeId) {
        return this.bodyType != null && bodyType.getId() == bodyTypeId;
    }

    public void changeBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
}
