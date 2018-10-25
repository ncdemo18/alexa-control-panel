package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.model.Constant;
import com.netcracker.alexa.controlpanel.vaadin.model.TrafficItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TrafficItemBlock extends VerticalLayout {
    private Image icon = new Image("/frontend/img/london/page1/icon_for_list.png", Constant.IMAGE_NOT_FOUND);
    private Label elementA;
    private Label elementE;
    private Label delay;

    private TrafficItem trafficItem;

    public TrafficItemBlock(TrafficItem trafficItem) {
        this.trafficItem  = trafficItem;

        elementA = new Label(trafficItem.getElementA());
        elementA.addClassName("white-font");
        elementA.getStyle().set("font-size", "24px");

        elementE = new Label(trafficItem.getElementE());
        elementE.addClassName("green-box");

        delay = new Label(trafficItem.getDelay());
        delay.addClassName("red-font");
        delay.getStyle().set("font-size", "22px");
        HorizontalLayout left = new HorizontalLayout(icon, elementA, elementE);

        delay.addClassName("align-element-right");
        HorizontalLayout layout = new HorizontalLayout(left, delay);

        setAlignItems(Alignment.STRETCH);

        add(layout);





    }
}
