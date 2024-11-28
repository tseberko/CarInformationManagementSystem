package edu.tseberko.carinformation.adapters.mvc.controllers.model;

import edu.tseberko.carinformation.adapters.mvc.controllers.Id;
import edu.tseberko.carinformation.ports.domains.models.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/models")
public class ModelController {

    private static final Logger log = LoggerFactory.getLogger(ModelController.class);

    private final ModelService modelService;
    private final BodyTypeService bodyTypeService;

    @Autowired
    public ModelController(ModelService modelService, BodyTypeService bodyTypeService) {
        this.modelService = modelService;
        this.bodyTypeService = bodyTypeService;
    }

    @GetMapping
    public String showModels(Model model) {
        log.info("Display of all models");
        model.addAttribute("modelsOfCars", modelService.getAllModels());
        return "models";
    }

    @GetMapping("/delete/{id}")
    public String deleteModelById(@PathVariable("id") long id, Model model) {
        log.info("Delete model by id {}", id);
        modelService.deleteModelById(id);
        return "redirect:/models";
    }

    @GetMapping("/new")
    public String showAddModelForm(Model model) {
        log.info("Filling data attributes for create new model");
        var bodyTypeIdNames = bodyTypeService.getAllBodyTypes().stream().map(BodyTypeView::reduceToIdAndName).toList();
        model.addAllAttributes(Map.of(
                "model", new ModelCreate(),
                "bodyTypeIdNames", bodyTypeIdNames
        ));
        return "add-model";
    }

    @PostMapping("/new")
    public ModelAndView addModel(@Valid @ModelAttribute("model") ModelCreate modelCreate, BindingResult result) {
        log.info("Create model with data from attributes");
        if (result.hasErrors()) {
            var bodyTypeIdNames = bodyTypeService.getAllBodyTypes().stream().map(BodyTypeView::reduceToIdAndName).toList();
            return new ModelAndView(
                    "add-model",
                    Map.of("model", modelCreate, "bodyTypeIdNames", bodyTypeIdNames)
            );
        }
        modelService.createNewModel(modelCreate.toCreateProperties());
        return new ModelAndView("redirect:/models");
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        log.info("Filling data attribute for update model with id: {}", id);
        var modelView = modelService.getModelById(id);
        model.addAttribute("model", new ModelUpdate().initValues(modelView));
        return "update-model";
    }

    @PostMapping("/update/{id}")
    public String updateModel(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("model") ModelUpdate modelUpdate,
            BindingResult result) {
        log.info("Update models with id: {}", id);
        if (result.hasErrors()) {
            return "update-model";
        }
        modelService.updateModelById(id, modelUpdate.toUpdateProperties());
        return "redirect:/models";
    }

    @GetMapping("/change-body-type/{id}")
    public String getChangeParent(@PathVariable long id, Model model) {
        log.info("Filling attributes for change bodyType of model with id {}", id);
        var modelView = modelService.getModelById(id);
        var bodyTypeIdNames = bodyTypeService.getAllBodyTypes()
                .stream()
                .filter(bodyTypeView -> bodyTypeView.idIsNot(modelView.bodyType().id()))
                .map(BodyTypeView::reduceToIdAndName)
                .toList();
        model.addAttribute("model", new ModelUpdate().initValues(modelView));
        model.addAttribute("bodyTypeIdNames", bodyTypeIdNames);
        model.addAttribute("id", new Id());
        return "change-model-body-type";
    }

    @PostMapping("/change-body-type/{id}")
    public String changeBodyType(
            @PathVariable("id") long id,
            @ModelAttribute("id") @Valid Id bodyTypeId,
            BindingResult bindingResult) {
        log.info("Change bodyType of model with id {}", id);
        if (bindingResult.hasErrors()) {
            return "change-model-body-type";
        }
        modelService.changeBodyType(id, bodyTypeId.getValue());
        return "redirect:/models";
    }
}
