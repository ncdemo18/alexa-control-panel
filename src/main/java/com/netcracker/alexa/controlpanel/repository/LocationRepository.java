package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
