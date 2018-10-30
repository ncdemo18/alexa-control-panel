package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import org.springframework.data.repository.CrudRepository;

public interface TemplateActionRepository extends CrudRepository<TemplateAction, Long> {
    TemplateAction findFirstByDescription(String description);

    boolean existsByDescription(String description);
}
