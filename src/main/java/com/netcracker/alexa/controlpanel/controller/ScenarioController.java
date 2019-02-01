package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.model.Command;
import com.netcracker.alexa.controlpanel.model.CommandType;
import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.repository.AlexaAnswerRepository;
import com.netcracker.alexa.controlpanel.repository.TemplateActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/script")
public class ScenarioController {
    private final Logger logger = LoggerFactory.getLogger(ScenarioController.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    @Autowired
    private TemplateActionRepository templateActionRepository;

    private int currentSlide = 0;
    private int countSlide = 10;
    private boolean isActiveCJM = false;

    @GetMapping("/")
    String script(Model model){
        logger.info("in method annotated @GetMapping('/script')");
        model.addAttribute("slide_number", currentSlide);
        model.addAttribute("is_active_cjm", isActiveCJM);
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
                break;
            case 2:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                break;
            case 3:
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.HIDE_TICKETS));
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.SHOW_TICKET_PANEL));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SHOW_TICKET_PANEL));
                break;
            case 4:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.GRANT_TICKET));
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.TAKE_TICKET));
                break;
            case 5:
                sendingOperations.convertAndSend("/topic/user/sam", new Command(CommandType.OPEN_CARTOON));
                break;
            case 6:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_LOYALTY_POINTS, "365"));
                break;
            case 7:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "2"));
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_LOYALTY_POINTS, "315"));
                break;
            case 8:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.OPEN_FOOTBALL));
                break;
            case 9:
                sendingOperations.convertAndSend("/topic/user/ricky", new Command(CommandType.SET_NUMBER_PAGE, "4"));
                break;
        }
        currentSlide = number;
        return "redirect:/script/";
    }

    @GetMapping("/change_plan")
    String changePlan(@RequestParam("isActiveCJM") boolean isActiveCJM){
        logger.info("use plan with cjm: ", isActiveCJM);
        this.isActiveCJM = isActiveCJM;
        save(isActiveCJM, "increase bandwidth to maximum");
        save(isActiveCJM, "increase band with to maximum");
        return "redirect:/script/";
    }

    private void save(boolean isActiveCJM, String phraseRequest) {
        AlexaAnswer alexaAnswer = alexaAnswerRepository.findFirstByPhraseRequest(phraseRequest);
        if (alexaAnswer != null) {
            if (isActiveCJM) {
                TemplateAction templateAction = templateActionRepository.findFirstByDescription("Jump to user page with number");
                ActionURL actionURL = new ActionURL("set_page?number_page=4", templateAction);
                List<ActionURL> list = new ArrayList<>();
                list.add(actionURL);
                alexaAnswer.setActions(list);
                alexaAnswer.setPhraseAnswer("Instead of temporal turbo boost I have a better idea: how about switching to more convenient internet plan?");
                alexaAnswerRepository.save(alexaAnswer);
            } else {
                alexaAnswer.setPhraseAnswer("Your plan’s bandwidth will be set to 150 megabits per second. This will result in a 20 euro monthly charge to your account. Do you wish to proceed?");
                alexaAnswer.setActions(Collections.emptyList());
                alexaAnswerRepository.save(alexaAnswer);
            }
        }
    }
}
