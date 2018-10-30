package com.netcracker.alexa.controlpanel;

import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;
import com.netcracker.alexa.controlpanel.model.db.entity.response.add.URLParam;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Element;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Location;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Page;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.ElementRepository;
import com.netcracker.alexa.controlpanel.repository.LocationRepository;
import com.netcracker.alexa.controlpanel.repository.TemplateActionRepository;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public void run(String... args) throws Exception {
        initialActionTemplates();
        initialUsers();
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
            london = locationRepository.save(london);

            Location dubai = new Location("dubai");
            locationRepository.save(dubai);

            User ricky = new User("Rick", "Boyle", "ricky", 6, 365, london, generateRickyPages());
            userRepository.save(ricky);

            User sam = new User("Sam", "Boyle", "sam", 1, 365, london, generateSamPages());
            userRepository.save(sam);
        }
    }

    private List<Page> generateRickyPages() {

        Page rickyPage1 = new Page(Arrays.asList(
                createElement("time_block", "right"),
                createElement("scroll_image", "1 Lock Screen")
        ));

        Page rickyPage2 = new Page(Arrays.asList(
                createElement("time_block", "left"),
                createElement("scroll_image", "2 Lock Screen"),
                createElement("score_block", "0 : 0"),
                createElement("loyalty_block", "365")
        ));

        Page rickyPage3 = new Page(Arrays.asList(
                createElement("loyalty_block", "365"),
                createElement("scroll_image", "3 Media Center")
        ));

        Page rickyPage4 = new Page(Arrays.asList(
                createElement("loyalty_block", "365"),
                createElement("scroll_image", "5 Match page")
        ));

        return Arrays.asList(rickyPage1, rickyPage2, rickyPage3, rickyPage4);
    }

    private List<Page> generateSamPages() {
        Page samPage = new Page(Arrays.asList(
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
}
