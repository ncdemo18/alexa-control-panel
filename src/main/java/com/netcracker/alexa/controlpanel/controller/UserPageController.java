package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Command;
import com.netcracker.alexa.controlpanel.model.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/{username}")
public class UserPageController {

    private final SimpMessageSendingOperations sendingOperations;

    @Autowired
    public UserPageController(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @GetMapping("/next_page")
    String nextPage(@PathVariable("username") String username) {
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.NEXT_PAGE));
        return "redirect:/";
    }

    @GetMapping("/set_page/{number_page}")
    String jumpToPage(@PathVariable("username") String username, @PathVariable("number_page") String numberPage) {
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_NUMBER_PAGE, numberPage));
        return "redirect:/";
    }

    @GetMapping("/set_location/{name_location}")
    String setLocation(@PathVariable("username") String username, @PathVariable("name_location") String nameLocation){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_LOCATION, nameLocation));
        return "redirect:/";
    }

    /*step 3*/
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

    /*step 4*/
    @GetMapping("/grant_ticket/{receive_user}")
    String grantTicket(@PathVariable("username") String grantUser, @PathVariable("receive_user") String receiveUser){
        sendingOperations.convertAndSend("/topic/user/" + grantUser, new Command(CommandType.GRANT_TICKET));
        sendingOperations.convertAndSend("/topic/user/" + receiveUser, new Command(CommandType.TAKE_TICKET));
        return "redirect:/";
    }

    /*step 5*/
    @GetMapping("/open_football")
    String openFootball(@PathVariable("username") String username){
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.OPEN_FOOTBALL));
        return "redirect:/";
    }
}