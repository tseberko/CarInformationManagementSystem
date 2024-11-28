package edu.tseberko.carinformation.ports.domains.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public BodyTypeService(BodyTypeRepository bodyTypeRepository, ModelRepository modelRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
        this.modelRepository = modelRepository;
    }

    @Transactional
    public BodyTypeView createNewBodyType(BodyTypePropertiesForCreate properties) {
        var bodyType = properties.containsParentId() ? properties.
                createBodyType(bodyTypeRepository::findById) : properties.createBodyType();
        return new BodyTypeView(this.bodyTypeRepository.save(bodyType));
    }

    @Transactional
    public BodyTypeView updateBodyTypeWithId(long id, BodyTypePropertiesForUpdate properties) {
        var bodyTypeForUpdate = bodyTypeRepository.findByIdFetchParent(id).
                orElseThrow(() -> new NotFoundException(id));
        bodyTypeForUpdate.update(properties);
        return new BodyTypeView(bodyTypeRepository.save(bodyTypeForUpdate));
    }

    @Transactional
    public void deleteBodyTypeById(long id) {
        if (bodyTypeRepository.countByParent_Id(id) > 0) {
            throw IllegalActionException.recordReferencedByAnotherRecords();
        }
//        if (modelRepository.countByBodyType_Id(id) > 0) {
//            throw IllegalActionException.bodyTypeReferencedByModelRecords();
//        }
        bodyTypeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BodyTypeView> getAllBodyTypes() {
        return bodyTypeRepository.findAllWithParent()
                .stream()
                .map(BodyTypeView::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public BodyTypeView getById(long id) {
        return bodyTypeRepository.findByIdFetchParent(id)
                .map(BodyTypeView::new)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public BodyTypeView changeParent(long id, long parentId) {
        var bodyType = bodyTypeRepository.findByIdFetchParent(id).orElseThrow(() -> new NotFoundException(id));
        if (bodyType.parentIdEquals(parentId)) {
            throw IllegalActionException.bodyTypeAlreadyAssigned(id, parentId);
        }
        var parent = bodyTypeRepository.findById(parentId).orElseThrow(() -> new NotFoundException(parentId));
        bodyType.changeParent(parent);
        return new BodyTypeView(bodyTypeRepository.save(bodyType));
    }

    @Transactional(readOnly = true)
    public BodyTypeParentOptions getBodyTypeAndParentOptions(long bodyTypeId) {
        var bodyType = bodyTypeRepository.findByIdFetchParent(bodyTypeId)
                .orElseThrow(() -> new NotFoundException(bodyTypeId));
        var bodyTypeViews = bodyTypeRepository.getAllParentCandidatesForBodyTypeWithId(bodyTypeId)
                .stream()
                .map(BodyTypeView::new)
                .toList();
        return new BodyTypeParentOptions(new BodyTypeView(bodyType), bodyTypeViews);
    }

}
