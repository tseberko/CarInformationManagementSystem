package edu.tseberko.carinformation.ports.domains.models;

import edu.tseberko.carinformation.ports.domains.models.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ModelService {

    private final ModelRepository modelRepository;
    private final BodyTypeRepository bodyTypeRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository, BodyTypeRepository bodyTypeRepository) {
        this.modelRepository = modelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
    }

    @Transactional
    public ModelView createNewModel(ModelPropertiesForCreate modelPropertiesForCreate) {
        BodyType bodyType = bodyTypeRepository.findById(modelPropertiesForCreate.bodyTypeId())
                .orElseThrow(() -> new NotFoundException(modelPropertiesForCreate.bodyTypeId()));
        var model = modelPropertiesForCreate.createModel(bodyType);
        modelRepository.save(model);
        return new ModelView(model);
    }

    @Transactional(readOnly = true)
    public List<ModelView> getAllModels() {
        return modelRepository.findAllWithBodyType()
                .stream()
                .map(ModelView::new)
                .toList();
    }

    @Transactional
    public ModelView updateModelById(long id, ModelPropertiesForUpdate modelPropertiesForUpdate) {
        var model = modelRepository.findByIdWithBodyType(id).orElseThrow(() -> new NotFoundException(id));
        model.update(modelPropertiesForUpdate);
        modelRepository.save(model);
        return new ModelView(model);
    }

    @Transactional
    public void deleteModelById(long id) {
        modelRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ModelView getModelById(long id) {
        return modelRepository.findByIdWithBodyType(id).map(ModelView::new).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public ModelView changeBodyType(long id, long bodyTypeId) {
        Model model = modelRepository.findByIdWithBodyType(id)
                .orElseThrow(() -> IllegalArgumentException.modelBodyTypeIdNotExists(id));
        if (model.bodyTypeIdEquals(bodyTypeId)) {
            throw IllegalStateException.exception(id, bodyTypeId);
        }
        var bodyType = bodyTypeRepository.findById(bodyTypeId).orElseThrow(() -> IllegalArgumentException.parentBodyTypeIdNotExists(bodyTypeId));
        model.changeBodyType(bodyType);
        return new ModelView(modelRepository.save(model));
    }
}
