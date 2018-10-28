package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Constant;
import com.netcracker.alexa.controlpanel.model.db.entity.AlexaRequest;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.AlexaRequestRepository;
import com.netcracker.alexa.controlpanel.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlexaController {

    private final AlexaAnswerRepository alexaAnswerRepository;

    private final AddressService addressService;

    private final AlexaRequestRepository alexaRequestRepository;

    private final Logger logger = LoggerFactory.getLogger(AlexaController.class);

    @Autowired
    public AlexaController(AlexaAnswerRepository alexaAnswerRepository, AddressService addressService, AlexaRequestRepository alexaRequestRepository) {
        logger.info("constructor for AlexaController");
        this.alexaAnswerRepository = alexaAnswerRepository;
        this.addressService = addressService;
        this.alexaRequestRepository = alexaRequestRepository;
    }

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String inputMessage) {
        logger.info("in /handle_user_request with inputMessage: '{}'", inputMessage);

        String phraseAnswer = Constant.ANSWER_FOR_UNRECOGNIZED_REQUEST;
        String searchVersionMessage = correctUserPhrase(inputMessage);

        alexaRequestRepository.save(new AlexaRequest(inputMessage, searchVersionMessage));

        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(searchVersionMessage);
        if (alexaAnswer != null) {
            logger.info("find answer for '{}'", searchVersionMessage);
            doActions(alexaAnswer);
            phraseAnswer = alexaAnswer.getPhraseAnswer();
        }
        return phraseAnswer;
    }

    private void doActions(AlexaAnswer alexaAnswer) {
        for (ActionURL actionURL : alexaAnswer.getActions()) {
            RestTemplate restTemplate = new RestTemplate();
            String addressAction = addressService.getFullURL(alexaAnswer.getUserLogin(), actionURL.getUrl());
            if(addressAction != null) {
                logger.info("do action for address: '{}'", addressAction);
                restTemplate.getForEntity(addressAction, String.class);
            }
        }
    }

    private String correctUserPhrase(String phrase) {
        return phrase.toLowerCase().replaceAll("[[^A-Za-zА-Яа-я0-9]\\s]", " ").replaceAll("\\s+", " ").trim();
    }
}
