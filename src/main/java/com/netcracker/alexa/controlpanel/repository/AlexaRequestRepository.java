package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.AlexaRequest;
import org.springframework.data.repository.CrudRepository;

public interface AlexaRequestRepository extends CrudRepository<AlexaRequest, Long> {
}
