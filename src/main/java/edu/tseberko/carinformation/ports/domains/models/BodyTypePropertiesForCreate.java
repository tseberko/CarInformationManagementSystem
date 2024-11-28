package edu.tseberko.carinformation.ports.domains.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.function.LongFunction;

public record BodyTypePropertiesForCreate(@NotBlank String name, String note, String description, Long parentId) {
    public boolean containsParentId() {
        return this.parentId != null;
    }

    public BodyType createBodyType() {
        return new BodyType(name, note, description);
    }

    public BodyType createBodyType(LongFunction<Optional<BodyType>> parentBodyType) {
        var parent = parentBodyType.apply(parentId)
                .orElseThrow(() -> IllegalArgumentException.parentIdNotFound(parentId));
        return new BodyType(name, note, description, parent);
    }

}
