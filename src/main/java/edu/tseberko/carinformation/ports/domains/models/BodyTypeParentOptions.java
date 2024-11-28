package edu.tseberko.carinformation.ports.domains.models;

import java.util.List;

public record BodyTypeParentOptions(BodyTypeView bodyTypeView, List<BodyTypeView> parentOptions) {
    public List<BodyTypeIdName> bodyTypeIdNames() {
        return parentOptions.stream().map(BodyTypeView::reduceToIdAndName).toList();
    }
}
