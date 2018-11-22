package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.model.Constant;
import com.netcracker.alexa.controlpanel.vaadin.model.NewItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NewBlock extends VerticalLayout {
    private Image image;
    private Label header;
    private Label time;

    private NewItem newItem;

    public NewBlock(NewItem newItem) {

        this.newItem = newItem;
        this.header = new Label(newItem.getHeader());
        this.header.addClassName("white-font");
        this.header.getStyle().set("font-size", "24px");

        this.time = new Label(newItem.getTime());
        this.time.addClassNames("gray-font");
        this.time.getStyle().set("font-size", "22px");

        Div newInfo = new Div(new Paragraph(header), new Paragraph(time));
        if(newItem.getSrcImage() != null) {
            this.image = new Image(newItem.getSrcImage(), Constant.IMAGE_NOT_FOUND);
            image.addClassName("image-in-block");
            Div newCard = new Div(image, newInfo);
            add(newCard);
        } else {
            add(newInfo);
        }
        setAlignItems(Alignment.STRETCH);
    }
}
