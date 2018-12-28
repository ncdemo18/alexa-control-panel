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
        logger.info("constructor for UserPageController");
        this.sendingOperations = sendingOperations;
    }

    @GetMapping("/next_page")
    String nextPage(@PathVariable("username") String username) {
        logger.info("next page for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.NEXT_PAGE));
        return "redirect:/";
    }

    @GetMapping("/set_page")
    String jumpToPage(@PathVariable("username") String username, @RequestParam("number_page") String numberPage) {
        logger.info("go to page {} for {}", numberPage, username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_NUMBER_PAGE, numberPage));
        return "redirect:/";
    }

    @GetMapping("/set_location")
    String setLocation(@PathVariable("username") String username, @RequestParam("name_location") String nameLocation){
        logger.info("set location {} for {}", nameLocation, username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_LOCATION, nameLocation));
        return "redirect:/";
    }

    @GetMapping("/show_tickets")
    String showTicketPanel(@PathVariable("username") String username){
        logger.info("show tickets for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SHOW_TICKET_PANEL));
        return "redirect:/";
    }

    @GetMapping("/hide_tickets")
    String hideTicketPanel(@PathVariable("username") String username){
        logger.info("hide tickets for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.HIDE_TICKETS));
        return "redirect:/";
    }

    @GetMapping("/grant_ticket")
    String grantTicket(@PathVariable("username") String grantUser, @RequestParam("receive_user") String receiveUser){
        logger.info("grant ticket to {} from {}", receiveUser, grantUser);
        sendingOperations.convertAndSend("/topic/user/" + grantUser, new Command(CommandType.GRANT_TICKET));
        sendingOperations.convertAndSend("/topic/user/" + receiveUser, new Command(CommandType.TAKE_TICKET));
        return "redirect:/";
    }

    @GetMapping("/open_football")
    String openFootball(@PathVariable("username") String username){
        logger.info("open football for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.OPEN_FOOTBALL));
        return "redirect:/";
    }

    @GetMapping("/change_temperature")
    String change(@PathVariable("username") String username, @RequestParam("temperature") String temperature){
        logger.info("change temperature for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.CHANGE_TEMPERATURE, temperature));
        return "redirect:/";
    }

    @GetMapping("/set_loyalty_point")
    String setLoyaltyPoints(@PathVariable("username") String username, @RequestParam("count_points") String countPoints){
        logger.info("set loyalty_points = {} for {}", countPoints, username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.SET_LOYALTY_POINTS, countPoints));
        return "redirect:/";
    }

    @GetMapping("/open_cartoon")
    String openCartoon(@PathVariable("username") String username){
        logger.info("open cartoon for {}", username);
        sendingOperations.convertAndSend("/topic/user/" + username, new Command(CommandType.OPEN_CARTOON));
        return "redirect:/";
    }
}
