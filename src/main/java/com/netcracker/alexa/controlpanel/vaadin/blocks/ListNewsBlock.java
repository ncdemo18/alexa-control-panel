package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.vaadin.model.NewItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class ListNewsBlock extends VerticalLayout {
    private Label header = new Label("HOTTEST NEWS");
    private ListBoxWithBorder<NewBlock> news = new ListBoxWithBorder<>();

    public ListNewsBlock(List<NewItem> newItemList) {
        header.addClassName("gray-font");
        header.getStyle().set("font-size", "24px");

        for(NewItem item : newItemList) {
            news.addElement(new NewBlock(item));
        }

        setClassName("page-block-gray");
        setAlignItems(Alignment.STRETCH);

        add(header, news);
    }
}
