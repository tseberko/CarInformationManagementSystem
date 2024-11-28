package edu.tseberko.carinformation.ports.domains.models;

import jakarta.validation.constraints.NotBlank;

public record ModelPropertiesForUpdate(@NotBlank String name, short numOfSeats, float maxSpeed) {
}
