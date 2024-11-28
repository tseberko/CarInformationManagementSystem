package edu.tseberko.carinformation.ports.domains.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.function.LongFunction;

public record BodyTypePropertiesForUpdate(@NotBlank String name, String note, String description) {
    public BodyTypePropertiesForUpdate(BodyTypeView bodyTypeView) {
        this(bodyTypeView.name(), bodyTypeView.note(), bodyTypeView.description());
    }
}
