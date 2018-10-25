package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ListBoxWithBorder<T extends VerticalLayout> extends ListBox<T> implements FlexComponent<T> {

    {
        setAlignItems(Alignment.STRETCH);
        addClassName("list-box");
    }

    public void addElement(T element) {
        element.addClassName("list-box-item");
        long count = getChildren().count();
        if(count > 0) {
            element.addClassName("item-with-border");
        }
        add(element);
    }
}
