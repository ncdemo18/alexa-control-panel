package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserRequestController {

/*@Autowired
    private SaveService saveService;


@MessageMapping("/start_config/{username}")
    @SendTo("/topic/user/{username}")
    public ConfigUserPages sendMessage(@DestinationVariable("username") String username) {
        System.out.println(username);
        return saveService.upload(username + "_config.json");
    }*/



    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/start_config/{login}")
    @SendTo("/topic/user/{login}")
    public User sendUserConfig(@DestinationVariable("login") String login) {
        System.out.println(login);
        return userRepository.findByLogin(login);
    }
}
