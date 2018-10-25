package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.vaadin.model.StockItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class StockBlock extends VerticalLayout {
    private Label name;
    private Label price;

    private List<StockItem> items;

    public StockBlock(StockItem stockItem) {
        name = new Label(stockItem.getName());
        price = new Label(stockItem.getPrice());

        price.addClassName("align-element-right");
        HorizontalLayout layout = new HorizontalLayout(name, price);

        addClassName("white-font");
        getStyle().set("font-size", "24px");

        setAlignItems(Alignment.STRETCH);

        add(layout);

    }
}
