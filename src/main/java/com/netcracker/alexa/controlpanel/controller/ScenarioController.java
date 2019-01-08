package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Command;
import com.netcracker.alexa.controlpanel.model.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/script")
public class ScenarioController {
    private final Logger logger = LoggerFactory.getLogger(ScenarioController.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    private int currentSlide = 0;
    private int countSlide = 8;

    @GetMapping("/")
    String script(Model model){
        logger.info("in method annotated @GetMapping('/script')");
        model.addAttribute("slide_number", currentSlide);
        return "script";
    }

    @GetMapping("/next_slide")
    private String nextSlide(){
        return "redirect:/script/slide?number_slide=" + ((currentSlide + 1) % countSlide);
    }

    @GetMapping("/slide")
    private String goToSlide(@RequestParam("number_slide") String numberSlide){
        int number = Integer.parseInt(numberSlide);
        switch (number){
            case 0:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "0"));
                break;
            case 1:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "1"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.CHANGE_TEMPERATURE, "17"));
                break;
            case 2:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "1"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.CHANGE_TEMPERATURE, "21"));
                break;
            case 3:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                break;
            case 4:
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.HIDE_TICKETS));
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.SHOW_TICKET_PANEL));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SHOW_TICKET_PANEL));
                break;
            case 5:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.GRANT_TICKET));
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.TAKE_TICKET));
                break;
            case 6:
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.OPEN_CARTOON));
                break;
            case 7:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.OPEN_FOOTBALL));
                break;
        }
        currentSlide = number;
        return "redirect:/script/";
    }
}
