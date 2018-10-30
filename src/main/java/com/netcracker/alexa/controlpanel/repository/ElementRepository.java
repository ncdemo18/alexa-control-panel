package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Element;
import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {
    Element findFirstByBlockNameAndParam(String blockName, String param);
}
