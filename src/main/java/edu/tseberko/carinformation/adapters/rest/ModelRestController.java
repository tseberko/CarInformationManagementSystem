package edu.tseberko.carinformation.adapters.rest;

import edu.tseberko.carinformation.ports.domains.models.ModelPropertiesForCreate;
import edu.tseberko.carinformation.ports.domains.models.ModelPropertiesForUpdate;
import edu.tseberko.carinformation.ports.domains.models.ModelService;
import edu.tseberko.carinformation.ports.domains.models.ModelView;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
public class ModelRestController {

    private final ModelService modelService;

    public ModelRestController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ModelView createNewModel(@RequestBody ModelPropertiesForCreate modelPropertiesForCreate) {
        return modelService.createNewModel(modelPropertiesForCreate);
    }

    @GetMapping
    public Models getAllModels() {
        return new Models(modelService.getAllModels());
    }

    @PutMapping("/{modelId}")
    public ModelView updateModelById(@PathVariable long modelId, @RequestBody ModelPropertiesForUpdate modelPropertiesForUpdate) {
        return modelService.updateModelById(modelId, modelPropertiesForUpdate);
    }

    @DeleteMapping("/{modelId}")
    public void deleteModelById(@PathVariable long modelId) {
        modelService.deleteModelById(modelId);
    }

    @GetMapping("/{modelId}")
    public ModelView getModelById(@PathVariable long modelId) {
        return modelService.getModelById(modelId);
    }

}
