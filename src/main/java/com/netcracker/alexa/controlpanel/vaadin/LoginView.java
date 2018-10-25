package com.netcracker.alexa.controlpanel.vaadin;

import com.netcracker.alexa.controlpanel.repository.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "login", layout = NCLayout.class)
public class LoginView extends VerticalLayout {
    private TextField login = new TextField();
    private PasswordField password = new PasswordField();
    private Button button = new Button("login");

    public LoginView(@Autowired UserRepository userRepository) {
        login.setPlaceholder("Login");
        password.setPlaceholder("Password");
        button.addClickListener(event -> {
            String userLogin = login.getValue();
            if(userRepository.existsUserByLogin(userLogin)){
                UI.getCurrent().navigate(ClientView.class, userLogin);
                //button.getUI().ifPresent(ui -> ui.navigate(ClientView.class, userLogin));
            }
            else {
                Notification.show("User don't found!");
            }
        });
        HorizontalLayout buttonsLayout = new HorizontalLayout(button);
        VerticalLayout divLayout = new VerticalLayout(login, password, buttonsLayout);
        divLayout.setSizeFull();
        divLayout.setAlignSelf(Alignment.END, buttonsLayout);
        Div loginDiv = new Div(divLayout);
        setAlignItems(Alignment.CENTER);
        add(loginDiv);
    }
}
