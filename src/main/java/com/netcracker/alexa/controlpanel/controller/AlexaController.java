package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Constant;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlexaController {

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    @Autowired
    private AddressService addressService;

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String message) {
        String phraseAnswer = Constant.ANSWER_FOR_UNRECOGNIZED_REQUEST;
        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(message);
        if (alexaAnswer != null) {
            doActions(alexaAnswer);
            phraseAnswer = alexaAnswer.getPhraseAnswer();
        }
        return phraseAnswer;
    }

    private void doActions(AlexaAnswer alexaAnswer) {
        for (ActionURL actionURL : alexaAnswer.getActions()) {
            String url = addressService.getFullURL(alexaAnswer.getUserLogin(), actionURL.getUrl());
            if(url != null) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForEntity(url, String.class);
            }
        }
    }
}
