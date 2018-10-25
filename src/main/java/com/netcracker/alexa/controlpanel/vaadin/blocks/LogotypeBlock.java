package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.model.Constant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LogotypeBlock extends VerticalLayout {
    private Image logotype = new Image("/frontend/img/london/page1/logotype.png", Constant.IMAGE_NOT_FOUND);
    private Image lock = new Image("/frontend/img/london/page1/lock.png", Constant.IMAGE_NOT_FOUND);
    public LogotypeBlock() {
        lock.addClassName("align-element-right");
        HorizontalLayout layout = new HorizontalLayout(logotype, lock);
        layout.setWidth("100%");
        setSizeFull();
        add(layout);
    }
}
