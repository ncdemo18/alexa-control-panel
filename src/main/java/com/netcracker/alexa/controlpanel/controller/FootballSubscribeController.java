package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.FootballScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FootballSubscribeController {

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @Autowired
    private FootballScore footballScore;

    @GetMapping("/add_football_score/{part}")
    String changeFootballScore(@PathVariable("part") String part) {
        if(part.equals("left")) {
            footballScore.addLeft();
        } else {
            footballScore.addRight();
        }
        sendingOperations.convertAndSend("/topic/football" , footballScore.toString());
        return "redirect:/";
    }

    @GetMapping("/reset_football_score")
    String resetFootballScore() {
        footballScore.resetScore();
        sendingOperations.convertAndSend("/topic/football" , footballScore.toString());
        return "redirect:/";
    }
}
