package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Command;
import com.netcracker.alexa.controlpanel.model.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/{username}")
public class UserPageController {

    private final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    private final SimpMessageSendingOperations sendingOperations;

    @Autowired
    public UserPageController(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @GetMapping("/next_page")
    String nextPage(@PathVariable("username") String username) {
        logger.debug("next page!!!");
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.NEXT_PAGE));
        return "redirect:/";
    }

    @GetMapping("/set_page")
    String jumpToPage(@PathVariable("username") String username, @RequestParam("number_page") String numberPage) {
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_NUMBER_PAGE, numberPage));
        return "redirect:/";
    }

    @GetMapping("/set_location")
    String setLocation(@PathVariable("username") String username, @RequestParam("name_location") String nameLocation){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_LOCATION, nameLocation));
        return "redirect:/";
    }

    @GetMapping("/show_tickets")
    String showTicketPanel(@PathVariable("username") String username){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SHOW_TICKET_PANEL));
        return "redirect:/";
    }

    @GetMapping("/hide_tickets")
    String hideTicketPanel(@PathVariable("username") String username){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.HIDE_TICKETS));
        return "redirect:/";
    }

    @GetMapping("/grant_ticket")
    String grantTicket(@PathVariable("username") String grantUser, @RequestParam("receive_user") String receiveUser){
        sendingOperations.convertAndSend("/topic/user/" + grantUser, new Command(CommandType.GRANT_TICKET));
        sendingOperations.convertAndSend("/topic/user/" + receiveUser, new Command(CommandType.TAKE_TICKET));
        return "redirect:/";
    }

    @GetMapping("/open_football")
    String openFootball(@PathVariable("username") String username){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.OPEN_FOOTBALL));
        return "redirect:/";
    }
}
