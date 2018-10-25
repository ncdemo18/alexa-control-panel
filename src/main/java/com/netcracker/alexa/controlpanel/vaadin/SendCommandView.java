package com.netcracker.alexa.controlpanel.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Push
@Route("")
public class SendCommandView extends Div {

    public SendCommandView() {
        TextField message = new TextField();
        message.setPlaceholder("You text");

        TextField user = new TextField();
        user.setPlaceholder("User login");

        Button send = new Button("Send", e -> {
            Broadcaster.broadcast(user.getValue(), message.getValue());
        });

        HorizontalLayout sendBar = new HorizontalLayout(user, message, send);
        add(sendBar);
    }
}
