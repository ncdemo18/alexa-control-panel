package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        String phraseAnswer = "Sorry, I don't understand you";
        message = correctUserPhrase(message);
        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(message);
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
        return phrase.toLowerCase().replaceAll("[[^A-Za-zА-Яа-я]\\s]", " ").replaceAll("\\s+", " ").trim();
    }
}
