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

            Location sweden = new Location("sweden");
            sweden = locationRepository.save(sweden);

            User ricky = new User("David", "Lindbergh", "ricky", 6, 365, sweden, generateRickyPages());
            userRepository.save(ricky);

            User sam = new User("Alice", "Lindbergh", "sam", 1, 365, sweden, generateSamPages());
            userRepository.save(sam);
        }
    }

    private List<Page> generateRickyPages() {

        Page rickyPage1 = new Page(Arrays.asList(
                createElement("time_block", "right"),
                createElement("scroll_image", "1 Lock Screen")
        ));

        Page rickyPage2 = new Page(Arrays.asList(
                createElement("user_name", ""),
                createElement("temperature_block", "16°"),
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

        return Arrays.asList(rickyPage1, rickyPage2, rickyPage3, rickyPage4);
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
                "assign 1 to alice",
                "Voice biometrics have been confirmed. One movie credit has been assigned to Alice.",
                getActions("grant_ticket?receive_user=sam", "Grant ticket to user")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "what is the temperature in alice s room",
                "It is 16 degrees Celcius"
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "make it 19 degrees",
                "Done.",
                getActions("change_temperature?temperature=19", "Change temperature")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "show me today s games",
                "Done.",
                getActions("set_page?number_page=3", "Jump to user page with number")
        ));

        addAnswer(new AlexaAnswer(
                "ricky",
                "i want to watch tonight s game between arsenal and everton",
                "As you wish.",
                getActions("open_football", "Open football for user")
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
        ));

        /*addAnswer(new AlexaAnswer(
                "ricky",
                "increase bandwidth to maximum",
                "Your plan’s bandwidth will be set to 150 megabits per second. This will result in a 20 euro monthly charge to your account. Do you wish to proceed?"
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "no that ridiculous",
                "You can triple your bandwidth for the next 3 hours. You can get this boost for 50 loyalty points. Would you like to go with this option?"
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "yes",
                "You got it—bandwidth is tripled for the next 3 hours. Enjoy!",
                getActions("set_loyalty_point?count_points=365", "Set loyalty points")
        ));
        addAnswer(new AlexaAnswer(
                "ricky",
                "i want to watch the game tonight",
                "Ok, you currently have on friend watching the game. Would you like to invite them?"
        ));*/

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
