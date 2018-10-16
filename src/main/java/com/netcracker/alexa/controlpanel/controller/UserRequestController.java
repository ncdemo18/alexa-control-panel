package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserRequestController {

    private final Logger logger = LoggerFactory.getLogger(UserRequestController.class);

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/start_config/{login}")
    @SendTo("/topic/user/{login}")
    public User sendUserConfig(@DestinationVariable("login") String login) {
        logger.debug("Send config to {}", login);
        return userRepository.findByLogin(login);
    }
}
