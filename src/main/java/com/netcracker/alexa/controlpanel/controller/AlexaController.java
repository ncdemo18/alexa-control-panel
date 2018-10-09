package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Command;
import com.netcracker.alexa.controlpanel.model.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlexaController {

    private final SimpMessageSendingOperations sendingOperations;

    @Autowired
    public AlexaController(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @GetMapping("/hello")
    String get(){
        return "";
    }

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String message){
        if(message.equals("next page")) {
            sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.NEXT_PAGE));
            //Обращение в базу и запрос фразы Алексы
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity("/user/ricky/next_page", String.class);
        }
        return "User message: " + message + "!";
    }
}
