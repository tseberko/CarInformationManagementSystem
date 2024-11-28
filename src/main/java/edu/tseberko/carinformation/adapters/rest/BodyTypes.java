package edu.tseberko.carinformation.adapters.rest;

import edu.tseberko.carinformation.ports.domains.models.BodyTypeView;

import java.util.List;

public record BodyTypes(List<BodyTypeView> bodyTypes) {
}
