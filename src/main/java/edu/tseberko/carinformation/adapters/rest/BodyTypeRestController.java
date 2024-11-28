package edu.tseberko.carinformation.adapters.rest;

import edu.tseberko.carinformation.ports.domains.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/body-types")
public class BodyTypeRestController {

    private final BodyTypeService bodyTypeService;

    @Autowired
    public BodyTypeRestController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @GetMapping
    public BodyTypes getAllBodyTypes() {
        return new BodyTypes(bodyTypeService.getAllBodyTypes());
    }

    @PostMapping
    public BodyTypeView createNew(@RequestBody BodyTypePropertiesForCreate properties) {
        return bodyTypeService.createNewBodyType(properties);
    }

    @PutMapping("/{bodyTypeId}")
    public BodyTypeView updateBodyType(@PathVariable long bodyTypeId, @Valid @RequestBody BodyTypePropertiesForUpdate properties) {
        return bodyTypeService.updateBodyTypeWithId(bodyTypeId, properties);
    }

    @DeleteMapping("/{bodyTypeId}")
    public void deleteBodyType(@PathVariable long bodyTypeId) {
        bodyTypeService.deleteBodyTypeById(bodyTypeId);
    }

    @GetMapping("/{bodyTypeId}")
    public BodyTypeView getBodyTypeById(@PathVariable long bodyTypeId) {
        return bodyTypeService.getById(bodyTypeId);
    }

    @PatchMapping("/{bodyTypeId}/parent")
    public BodyTypeView changeParent(@PathVariable long bodyTypeId, @RequestBody ParentId parentId) {
        return bodyTypeService.changeParent(bodyTypeId, parentId.id());
    }

    @GetMapping("/{bodyTypeId}/parent-options")
    public BodyTypeParentOptions getParentOptions(@PathVariable long bodyTypeId) {
        return bodyTypeService.getBodyTypeAndParentOptions(bodyTypeId);
    }

}
