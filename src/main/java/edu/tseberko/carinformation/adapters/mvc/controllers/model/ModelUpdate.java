package edu.tseberko.carinformation.adapters.mvc.controllers.model;

import edu.tseberko.carinformation.ports.domains.models.ModelPropertiesForUpdate;
import edu.tseberko.carinformation.ports.domains.models.ModelView;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class ModelUpdate implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    @Min(1)
    @Max(8)
    private short numOfSeats;
    @Min(1)
    @Max(1240)
    private float maxSpeed;

    public ModelPropertiesForUpdate toUpdateProperties() {
        return new ModelPropertiesForUpdate(this.name, this.numOfSeats, this.maxSpeed);
    }

    public ModelUpdate initValues(ModelView view) {
        this.id = view.id();
        this.name = view.name();
        this.numOfSeats = view.numOfSeats();
        this.maxSpeed = view.maxSpeed();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(short numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelUpdate that = (ModelUpdate) o;

        if (numOfSeats != that.numOfSeats) return false;
        if (Float.compare(maxSpeed, that.maxSpeed) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) numOfSeats;
        result = 31 * result + (maxSpeed != 0.0f ? Float.floatToIntBits(maxSpeed) : 0);
        return result;
    }
}
