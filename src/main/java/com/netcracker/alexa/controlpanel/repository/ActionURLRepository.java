package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import org.springframework.data.repository.CrudRepository;

public interface ActionURLRepository extends CrudRepository<ActionURL, Long> {
    ActionURL findFirstByUrl(String url);
    boolean existsByUrl(String url);
}