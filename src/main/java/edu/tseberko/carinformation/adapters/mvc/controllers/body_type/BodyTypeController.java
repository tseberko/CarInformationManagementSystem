package edu.tseberko.carinformation.adapters.mvc.controllers.body_type;

import edu.tseberko.carinformation.adapters.mvc.controllers.Id;
import edu.tseberko.carinformation.ports.domains.models.BodyTypeService;
import edu.tseberko.carinformation.ports.domains.models.BodyTypeView;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/body-types")
public class BodyTypeController {

    private static final Logger log = LoggerFactory.getLogger(BodyTypeController.class);
    private final BodyTypeService bodyTypeService;

    @Autowired
    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @GetMapping
    public String display(Model model) {
        log.info("Display all bodyType");
        model.addAttribute("bodyTypes", bodyTypeService.getAllBodyTypes());
        return "body-types";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        log.info("Delete bodyType with id {}", id);
        bodyTypeService.deleteBodyTypeById(id);
        return "redirect:/body-types";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable long id, Model model) {
        log.info("Filling data attributes for update new bodyType with id: {}", id);
        BodyTypeView bodyTypeView = bodyTypeService.getById(id);
        model.addAttribute("bodyType", new BodyTypeUpdate().init(bodyTypeView));
        return "update-body-type";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable long id,
            @Valid @ModelAttribute("bodyType") BodyTypeUpdate bodyType,
            BindingResult result) {
        log.info("Update bodyType with id: {}", id);
        if (result.hasErrors()) {
            return "update-body-type";
        }
        bodyTypeService.updateBodyTypeWithId(id, bodyType.toUpdateProperties());
        return "redirect:/body-types";
    }

    @GetMapping("/new")
    public String newBodyType(Model model) {
        log.info("Filling data attributes for create new model");
        var bodyTypeIdNames = bodyTypeService.getAllBodyTypes().stream().map(BodyTypeView::reduceToIdAndName).toList();
        model.addAttribute("bodyTypeIdNames", bodyTypeIdNames);
        model.addAttribute("bodyTypeForCreate", new BodyTypeCreate());
        return "persist-body-type";
    }

    @PostMapping("/persist")
    public ModelAndView newBodyType(
            @Valid @ModelAttribute("bodyTypeForCreate") BodyTypeCreate bodyTypeCreate,
            BindingResult result) {
        log.info("Create new bodyType with data from attributes");
        if (result.hasErrors()) {
            var bodyTypeIdNames = bodyTypeService.getAllBodyTypes().stream().map(BodyTypeView::reduceToIdAndName).toList();
            return new ModelAndView(
                    "persist-body-type",
                    Map.of(
                            "bodyTypeForCreate", bodyTypeCreate,
                            "bodyTypeIdNames", bodyTypeIdNames
                    )
            );
        }
        bodyTypeService.createNewBodyType(bodyTypeCreate.toCreateProperties());
        return new ModelAndView("redirect:/body-types");
    }

    @GetMapping("/change-parent/{id}")
    public String getChangeParent(@PathVariable long id, Model model) {
        log.info("Filling data attributes for change parent bodyType with id {}", id);
        var bodyTypeParentOptions = bodyTypeService.getBodyTypeAndParentOptions(id);
        model.addAttribute("bodyType", new BodyTypeUpdate().init(bodyTypeParentOptions.bodyTypeView()));
        model.addAttribute("bodyTypeIdNames", bodyTypeParentOptions.bodyTypeIdNames());
        model.addAttribute("parentId", new Id());
        return "change-body-type-parent";
    }

    @PostMapping("/change-parent/{id}")
    public String changeParent(
            @PathVariable long id,
            @ModelAttribute("parentId") @Valid Id parentId,
            BindingResult result) {
        log.info("Change parent bodyType id from {} to {}", id, parentId);
        if (result.hasErrors()) {
            return "change-body-type-parent";
        }
        bodyTypeService.changeParent(id, parentId.getValue());
        return "redirect:/body-types";
    }
}