package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.AlexaRequest;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.AlexaRequestRepository;
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

    @Autowired
    private AlexaRequestRepository alexaRequestRepository;

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String inputMessage) {
        String phraseAnswer = "Sorry, I don't understand you";
        String searchVersionMessage = correctUserPhrase(inputMessage);

        alexaRequestRepository.save(new AlexaRequest(inputMessage, searchVersionMessage));

        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(searchVersionMessage);
        if (alexaAnswer != null) {
            for (ActionURL actionURL : alexaAnswer.getActions()) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForEntity(addressService.getFullURL(alexaAnswer.getUserLogin(), actionURL.getUrl()), String.class);
            }
            phraseAnswer = alexaAnswer.getPhraseAnswer();
        }
        return phraseAnswer;
    }
    private String correctUserPhrase(String phrase) {
        return phrase.toLowerCase().replaceAll("[[^A-Za-zА-Яа-я0-9]\\s]", " ").replaceAll("\\s+", " ").trim();
    }
}
