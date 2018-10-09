package com.netcracker.alexa.controlpanel.repository;

import com.netcracker.alexa.controlpanel.model.db.entity.response.AlexaAnswer;
import org.springframework.data.repository.CrudRepository;

public interface AlexaAnswerRepository extends CrudRepository<AlexaAnswer, Long> {
    AlexaAnswer findFirstByPhraseRequest(String phraseRequest);
    boolean existsAlexaAnswerByPhraseRequest(String phraseRequest);
}
