package edu.tseberko.carinformation.adapters.mvc.controllers.model;

import edu.tseberko.carinformation.ports.domains.models.ModelPropertiesForCreate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ModelCreate {
    @NotBlank
    private String name;
    private long bodyTypeId;
    @Min(1)
    @Max(8)
    private short numOfSeats;
    @Min(1)
    @Max(1240)
    private float maxSpeed;

    public ModelPropertiesForCreate toCreateProperties() {
        return new ModelPropertiesForCreate(this.name, this.bodyTypeId, this.numOfSeats, this.maxSpeed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(long bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
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

        ModelCreate that = (ModelCreate) o;

        if (bodyTypeId != that.bodyTypeId) return false;
        if (numOfSeats != that.numOfSeats) return false;
        if (Float.compare(maxSpeed, that.maxSpeed) != 0) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (bodyTypeId ^ (bodyTypeId >>> 32));
        result = 31 * result + numOfSeats;
        result = 31 * result + (maxSpeed != 0.0f ? Float.floatToIntBits(maxSpeed) : 0);
        return result;
    }
}
