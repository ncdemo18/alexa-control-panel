package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.response.Action;
import com.netcracker.alexa.controlpanel.model.db.entity.response.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.ActionRepository;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControlPanelController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

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
    String editUserData(Model model) {
        List<Action> actions = (List<Action>) actionRepository.findAll();
        model.addAttribute("actions", actions);
        return "ricky";
    }

    @PostMapping("/add_action")
    String editUserData(@RequestParam("login") String login,
                        @RequestParam("userPhrase") String userPhrase,
                        @RequestParam("alexaAnswer") String alexaAnswer,
                        @RequestParam("typeAction") String typeActionId,
                        @RequestParam("paramAction") String paramAction) {
        System.out.println("login: " + login);
        System.out.println("userPhrase: " + userPhrase);
        System.out.println("alexaAnswer: " + alexaAnswer);
        System.out.println("typeAction: " + typeActionId);
        if(!alexaAnswerRepository.existsAlexaAnswerByPhraseRequest(userPhrase)) {
            AlexaAnswer answer = new AlexaAnswer(login, userPhrase, alexaAnswer);
            if(typeActionId != null && !typeActionId.isEmpty()) {
                Action action = actionRepository.findById(Long.valueOf(typeActionId)).orElse(null);
                if(action != null) {
                    answer.addAction(action);
                }
            }
            alexaAnswerRepository.save(answer);
        }
        return "ricky";
    }
}
