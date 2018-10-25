package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.TemplateActionRepository;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import com.netcracker.alexa.controlpanel.vaadin.Broadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private TemplateActionRepository templateActionRepository;

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    private final Logger logger = LoggerFactory.getLogger(ControlPanelController.class);

    @GetMapping("/start")
    String startPage(){
        Broadcaster.broadcast("ricky", "Hello from controller");
        return "index1";
    }

    @GetMapping("/test")
    String test(){
        return "test";
    }

    @GetMapping("/authorization/{login}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    String authorization(@PathVariable("login") String login){
        logger.debug("authorization: {}", login);
        if(userRepository.existsUserByLogin(login)) {
            return "yes";
        }
        return "no";
    }

    @GetMapping("/add_answer")
    String add_answer(Model model) {
        List<TemplateAction> actions = (List<TemplateAction>) templateActionRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("template_actions", actions);
        model.addAttribute("users", users);
        model.addAttribute("answer", new AlexaAnswer());
        return "add_new_answer";
    }

    @PostMapping("/add_answer")
    String addAnswer(@ModelAttribute("answer") AlexaAnswer answer) {
        answer.correctUserPhrase();
        if(!alexaAnswerRepository.existsAlexaAnswerByPhraseRequest(answer.getPhraseRequest())) {
            alexaAnswerRepository.save(answer);
        }
        return "redirect:/show_phrase";
    }

    @GetMapping("/show_phrase")
    String showListPhrase(Model model) {
        List<AlexaAnswer> answers = (List<AlexaAnswer>) alexaAnswerRepository.findAll();
        model.addAttribute("answers", answers);
        return "list_phrase";
    }

    @GetMapping("/edit_user_phrase")
    String editUserPhrase(@RequestParam("idAnswer") long idAnswer, Model model) {
        AlexaAnswer answer = alexaAnswerRepository.findById(idAnswer).orElse(null);
        model.addAttribute("answer", answer);
        model.addAttribute("template_actions", templateActionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "edit_phrase";
    }

    @PostMapping("/edit_user_phrase")
    String editPhrase(@ModelAttribute("answer") AlexaAnswer answer) {
        answer.correctUserPhrase();
        alexaAnswerRepository.save(answer);
        return "redirect:/show_phrase";
    }

    @PostMapping("/delete_user_phrase")
    String deletePhrase(@RequestParam("idAnswer") long idAnswer) {
        alexaAnswerRepository.deleteById(idAnswer);
        return "redirect:/show_phrase";
    }

    @GetMapping("/clear_all")
    String clearAll() {
        alexaAnswerRepository.deleteAll();
        return "redirect:/show_phrase";
    }
}
