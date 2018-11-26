package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.db.entity.AlexaRequest;
import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.*;
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

    private final UserRepository userRepository;

    private final TemplateActionRepository templateActionRepository;

    private final AlexaAnswerRepository alexaAnswerRepository;

    private final AlexaRequestRepository alexaRequestRepository;

    private final LocationRepository locationRepository;

    private final Logger logger = LoggerFactory.getLogger(ControlPanelController.class);

    @Autowired
    public ControlPanelController(UserRepository userRepository,
                                  TemplateActionRepository templateActionRepository,
                                  AlexaAnswerRepository alexaAnswerRepository,
                                  AlexaRequestRepository alexaRequestRepository,
                                  LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        logger.info("constructor for ControlPanelController");
        this.userRepository = userRepository;
        this.templateActionRepository = templateActionRepository;
        this.alexaAnswerRepository = alexaAnswerRepository;
        this.alexaRequestRepository = alexaRequestRepository;
    }

    @GetMapping("/")
    String startPage(Model model){
        logger.info("in method annotated @GetMapping('/')");
        model.addAttribute("locations", locationRepository.findAll());
        return "index";
    }

    @GetMapping("/test")
    String test(){
        logger.info("in method annotated @GetMapping('/test')");
        return "test";
    }

    @GetMapping("/authorization/{login}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    String authorization(@PathVariable("login") String login){
        logger.info("authorization: {}", login);
        if(userRepository.existsUserByLogin(login)) {
            logger.info("find user with login '{}'", login);
            return "yes";
        }
        logger.info("user with login '{}' not found", login);
        return "no";
    }

    @GetMapping("/add_answer")
    String add_answer(Model model) {
        logger.info("in method annotated @GetMapping('/add_answer')");
        List<TemplateAction> actions = (List<TemplateAction>) templateActionRepository.findAll();
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("template_actions", actions);
        model.addAttribute("users", users);
        model.addAttribute("answer", new AlexaAnswer());
        return "add_new_answer";
    }

    @PostMapping("/add_answer")
    String addAnswer(@ModelAttribute("answer") AlexaAnswer answer) {
        logger.info("in method annotated @PostMapping('/add_answer')");
        logger.info("user phrase: {}", answer.getPhraseAnswer());
        answer.correctUserPhrase();
        if(!alexaAnswerRepository.existsAlexaAnswerByPhraseRequest(answer.getPhraseRequest())) {
            alexaAnswerRepository.save(answer);
        }
        return "redirect:/show_phrase";
    }

    @GetMapping("/show_phrase")
    String showListPhrase(Model model) {
        logger.info("in method annotated @GetMapping('/show_phrase')");
        List<AlexaAnswer> answers = (List<AlexaAnswer>) alexaAnswerRepository.findAll();

        model.addAttribute("answers", answers);
        return "list_phrase";
    }

    @GetMapping("/show_request")
    String showListRequest(Model model) {
        logger.info("in method annotated @GetMapping('/show_request')");
        List<AlexaRequest> requests = (List<AlexaRequest>) alexaRequestRepository.findAll();

        model.addAttribute("alexa_requests", requests);
        return "list_alexa_request";
    }

    @GetMapping("/edit_user_phrase")
    String editUserPhrase(@RequestParam("idAnswer") long idAnswer, Model model) {
        logger.info("in method annotated @GetMapping('/edit_user_phrase')");
        logger.info("param 'idAnswer': {}", idAnswer);
        AlexaAnswer answer = alexaAnswerRepository.findById(idAnswer).orElse(null);
        model.addAttribute("answer", answer);
        model.addAttribute("template_actions", templateActionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "edit_phrase";
    }

    @PostMapping("/edit_user_phrase")
    String editPhrase(@ModelAttribute("answer") AlexaAnswer answer) {
        logger.info("in method annotated @PostMapping('/edit_user_phrase')");
        logger.info("user phrase: {}", answer.getPhraseAnswer());
        answer.correctUserPhrase();
        alexaAnswerRepository.save(answer);
        return "redirect:/show_phrase";
    }

    @PostMapping("/delete_user_phrase")
    String deletePhrase(@RequestParam("idAnswer") long idAnswer) {
        logger.info("in method annotated @PostMapping('/delete_user_phrase')");
        logger.info("param 'idAnswer': {}", idAnswer);
        alexaAnswerRepository.deleteById(idAnswer);
        return "redirect:/show_phrase";
    }

    @PostMapping("/delete_request_phrase")
    String deleteRequestPhrase(@RequestParam("idRequest") long idRequest) {
        logger.info("in method annotated @PostMapping('/delete_user_phrase')");
        logger.info("param 'idRequest': {}", idRequest);
        alexaRequestRepository.deleteById(idRequest);
        return "redirect:/show_request";
    }

    @GetMapping("/clear_all_actions")
    String clearAllActions() {
        logger.info("in method annotated @GetMapping('/clear_all_actions')");
        alexaAnswerRepository.deleteAll();
        return "redirect:/show_phrase";
    }

    @GetMapping("/clear_all_requests")
    String clearAll() {
        logger.info("in method annotated @GetMapping('/clear_all_requests')");
        alexaRequestRepository.deleteAll();
        return "redirect:/show_request";
    }
}
