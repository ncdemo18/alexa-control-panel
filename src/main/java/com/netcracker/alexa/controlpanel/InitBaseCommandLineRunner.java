package com.netcracker.alexa.controlpanel;

import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import com.netcracker.alexa.controlpanel.model.db.entity.response.add.URLParam;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.ActionURL;
import com.netcracker.alexa.controlpanel.model.db.entity.response.handle.AlexaAnswer;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Element;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Location;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Page;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitBaseCommandLineRunner implements CommandLineRunner {
    @Autowired
    private TemplateActionRepository templateActionRepository;

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlexaAnswerRepository alexaAnswerRepository;

    @Override
    public void run(String... args) throws Exception {
        initialActionTemplates();
        initialUsers();
        initialStandardAlexaRequest();
    }

    private void initialActionTemplates() {
        createActionTemplate("Go to next user page", "next_page", null);
        createActionTemplate("Jump to user page with number", "set_page", new URLParam("number_page"));
        createActionTemplate("Set user location", "set_location", new URLParam("name_location"));
        createActionTemplate("Show ticket panel on user page", "show_tickets", null);
        createActionTemplate("Hide ticket panel on user page", "hide_tickets", null);
        createActionTemplate("Grant ticket to user", "grant_ticket", new URLParam("receive_user"));
        createActionTemplate("Open football for user", "open_football", null);
        createActionTemplate("Open DMP", "open_dmp", null);
        createActionTemplate("Open cartoon for user", "open_cartoon", null);
        createActionTemplate("Set loyalty points", "set_loyalty_point", new URLParam("count_points"));
        createActionTemplate("Change temperature", "change_temperature", new URLParam("temperature"));
    }

    private void createActionTemplate(String description, String url, URLParam param) {
        if (!templateActionRepository.existsByDescription(description)) {
            TemplateAction template = new TemplateAction(description, url);
            if (param != null) {
                template.addParam(param);
            }
            templateActionRepository.save(template);
        }
    }

    private void initialUsers() {
        if(!userRepository.existsUserByLogin("ricky")) {
            Location london = new Location("london");
            locationRepository.save(london);

            Location dubai = new Location("dubai");
            locationRepository.save(dubai);

            Location rome = new Location("rome");
            locationRepository.save(rome);

           /* Location sweden = new Location("sweden");
            sweden = locationRepository.save(sweden);*/

            Location german = new Location("german");
            german = locationRepository.save(german);

            User ricky = new User("Walter", "", "ricky", 6, 365, german, generateRickyPages());
            userRepository.save(ricky);

            User sam = new User("Emma", "", "sam", 1, 365, german, generateSamPages());
            userRepository.save(sam);
        }

        //hardcode for o2, sorry if you see it, not only this block, but all this terrible!
        if(!userRepository.existsUserByLogin("o2")) {
            Location o2Location = new Location("o2");
            o2Location = locationRepository.save(o2Location);
            User o2User = new User("O2", "", "o2", 0, 0, o2Location, generateO2Pages());
            userRepository.save(o2User);
        }
    }

    private List<Page> generateO2Pages() {
        Page o2Page1 = new Page(Arrays.asList(
                createElement("scroll_image", "Image 1")
        ));
        Page o2Page2 = new Page(Arrays.asList(
                createElement("scroll_image", "Image 2")
        ));
        return Arrays.asList(o2Page1, o2Page2);
    }

    private List<Page> generateRickyPages() {

        Page rickyPage1 = new Page(Arrays.asList(
                createElement("time_block", "right"),
                createElement("scroll_image", "1 Lock Screen")
        ));

        Page rickyPage2 = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("temperature_block", "17°"),
                createElement("time_block", "left"),
                createElement("scroll_image", "2 Lock Screen"),
                /*createElement("score_block", "0 : 0"),*/
                createElement("loyalty_block loyalty_down", "365")
        ));

        Page rickyPage3 = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("loyalty_block loyalty_up", "365"),
                createElement("scroll_image", "3 Media Center")
        ));

        Page rickyPage4 = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("loyalty_block loyalty_up", "365"),
                createElement("scroll_image", "5 Match page")
        ));

        Page rickyPage5 = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("loyalty_block loyalty_up", "365"),
                createElement("scroll_image", "5 Surf gigaspeed")
        ));

        return Arrays.asList(rickyPage1, rickyPage2, rickyPage3, rickyPage4, rickyPage5);
    }

    private List<Page> generateSamPages() {
        Page samPage = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("scroll_image_video", "10 Media Center - Pass recieved")
        ));
        return Arrays.asList(samPage);
    }

    private Element createElement(String blockName, String param) {
        Element element = elementRepository.findFirstByBlockNameAndParam(blockName, param);
        if (element == null) {
            element = elementRepository.save(new Element(blockName, param));
        }
        return element;
    }

    private void initialStandardAlexaRequest(){
        Map<String, String> list = new HashMap<>();
        list.put("set_page?number_page=2", "Jump to user page with number");
        list.put("show_tickets", "Show ticket panel on user page");
        addAnswer(new AlexaAnswer(
                "ricky",
                "how many movies do i have",
                "You have 7 movie credits available.",
                getActions(list)

        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "assign 1 to emma",
                "Voice biometrics have been confirmed. One movie credit has been assigned to Emma.",
                getActions("grant_ticket?receive_user=sam", "Grant ticket to user")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "share 1 with emma",
                "Voice biometrics have been confirmed. One movie credit has been assigned to Emma.",
                getActions("grant_ticket?receive_user=sam", "Grant ticket to user")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "sure 1 with emma",
                "Voice biometrics have been confirmed. One movie credit has been assigned to Emma.",
                getActions("grant_ticket?receive_user=sam", "Grant ticket to user")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "chair 1 with emma",
                "Voice biometrics have been confirmed. One movie credit has been assigned to Emma.",
                getActions("grant_ticket?receive_user=sam", "Grant ticket to user")
        ));



     /*    addAnswer(new AlexaAnswer(
                "ricky",
                "what is the temperature in alice s room",
                "It is 17 degrees Celsius",
                getActions("set_page?number_page=1", "Jump to user page with number")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "make it 21",
                "Done.",
                getActions("change_temperature?temperature=21", "Change temperature")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "show me today s games",
                "Done.",
                getActions("set_page?number_page=3", "Jump to user page with number")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "show me what s on tonight",
                "Here you go.",
                getActions("set_page?number_page=2", "Jump to user page with number")
        ));

       addAnswer(new AlexaAnswer(
                "ricky",
                "i want to watch tonight s game between arsenal and everton",
                "As you wish.",
                getActions("open_football", "Open football for user")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "i d like to see the latest available eon offerings",
                "As you wish.",
                getActions("open_dmp", "Open DMP")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "dim the lights in the living room",
                "Done."
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "stop the game",
                "Done."
        ));*/

       addAnswer(new AlexaAnswer(
                "ricky",
                "increase bandwidth to maximum",
                "Your plan’s bandwidth will be set to 150 megabits per second. This will result in a 20 euro monthly charge to your account. Do you wish to proceed?"
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "increase band with to maximum",
                "Your plan’s bandwidth will be set to 150 megabits per second. This will result in a 20 euro monthly charge to your account. Do you wish to proceed?"
        ));

        /*addAnswer(new AlexaAnswer(
                "ricky",
                "increase bandwidth to maximum",
                "Instead of temporal turbo boost I have a better idea: how about switching to more convenient internet plan?",
                getActions("set_page?number_page=4", "Jump to user page with number")
        ));*/

        addAnswer(new AlexaAnswer(
                "ricky",
                "yes please",
                "Got that. Your service provider will contact you shortly with possible installation dates. Now enjoy your free turbo boost.",
                getActions("set_page?number_page=4", "Jump to user page with number")
        ));

         addAnswer(new AlexaAnswer(
                "ricky",
                "no that ridiculous",
                "You can triple your bandwidth for the next 3 hours. You can get this boost for 50 loyalty points. Would you like to go with this option?"
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "no that s ridiculous",
                "You can triple your bandwidth for the next 3 hours. You can get this boost for 50 loyalty points. Would you like to go with this option?"
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "yes",
                "You got it—bandwidth is tripled for the next 3 hours. Enjoy!",
                getActions("set_loyalty_point?count_points=315", "Set loyalty points")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "i want to watch the game tonight",
                "Go Fortuna!",
                getActions("open_football", "Open football for user")
        ));

    }

    private List<ActionURL> getActions(String url, String description){
        TemplateAction templateAction = templateActionRepository.findFirstByDescription(description);
        ActionURL actionURL = new ActionURL(url, templateAction);
        return Arrays.asList(actionURL);
    }

    private List<ActionURL> getActions(Map<String, String> list){
        List<ActionURL> actions = new ArrayList<>();
        for(String url: list.keySet()){
            TemplateAction templateAction = templateActionRepository.findFirstByDescription(list.get(url));
            ActionURL actionURL = new ActionURL(url, templateAction);
            actions.add(actionURL);
        }
        return actions;
    }

    private void addAnswer(AlexaAnswer answer){
        if(!alexaAnswerRepository.existsAlexaAnswerByPhraseRequest(answer.getPhraseRequest())) {
            alexaAnswerRepository.save(answer);
        }
    }
}
