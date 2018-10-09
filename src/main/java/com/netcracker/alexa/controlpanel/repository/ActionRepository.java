package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.response.Action;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action, Long> {
    Action findFirstByDescription(String description);
}
