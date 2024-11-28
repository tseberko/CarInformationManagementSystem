package edu.tseberko.carinformation.adapters.mvc.controllers;

import jakarta.validation.constraints.Min;

import java.util.Objects;

public class Id {

    @Min(1)
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return value == id.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
