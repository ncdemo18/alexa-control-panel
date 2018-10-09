package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.response.Action;
import com.netcracker.alexa.controlpanel.model.db.entity.response.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlexaController {

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    private String applicationURL = "https://alexa-control-panel.herokuapp.com/";


    @GetMapping("/hello")
    String get(){
        return "";
    }

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String message){
        String phraseAnswer = "Sorry, I don't understand you";
        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(message);
        if(alexaAnswer != null) {
            for (Action action : alexaAnswer.getActions()){
                if(action.isRequiredAction()){
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getForEntity(applicationURL + action.getActionURL(alexaAnswer.getUserLogin()), String.class);
                }
            }
            phraseAnswer = alexaAnswer.getPhraseAnswer();
        }
        return phraseAnswer;
    }
}
