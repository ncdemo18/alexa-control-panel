package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.response.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControlPanelController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    @GetMapping("/")
    String startPage(){
        return "index";
    }

    @GetMapping("/authorization/{username}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    String authorization(@PathVariable("username") String username){
        System.out.println("authorization: " + username);
        if(userRepository.existsUserByUsername(username)) {
            return "yes";
        }
        return "no";
    }

    @GetMapping("/add_action")
    String editUserData() {
        return "ricky";
    }

    @PostMapping("/add_action")
    String editUserData(Model model,
                        @RequestParam("login") String login,
                        @RequestParam("userPhrase") String userPhrase,
                        @RequestParam("alexaAnswer") String alexaAnswer) {
        AlexaAnswer answer = new AlexaAnswer(userPhrase, alexaAnswer, login);
        alexaAnswerRepository.save(answer);
        return "ricky";
    }
}
