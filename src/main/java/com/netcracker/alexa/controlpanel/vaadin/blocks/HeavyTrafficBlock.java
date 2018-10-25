package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.vaadin.model.TrafficItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementFactory;

import java.util.List;

public class HeavyTrafficBlock extends VerticalLayout {
    private Div numberItem = new Div();
    private Div header = new Div();
    private ListBoxWithBorder<TrafficItemBlock> trafficInfo = new ListBoxWithBorder<>();

    public HeavyTrafficBlock(List<TrafficItem> trafficItems, String number) {

        header.getElement().appendChild(
                ElementFactory.createSpan("Heavy"),
                ElementFactory.createBr(),
                ElementFactory.createSpan("traffic"));

        header.addClassName("red-font");
        header.getStyle().set("font-size", "28px");
        header.getStyle().set("align-self", "center");

        Span span = new Span(number);
        span.addClassName("text-in-circle");

        numberItem.add(span);
        numberItem.addClassName("circle");

        HorizontalLayout layout = new HorizontalLayout(numberItem, header);
        layout.getStyle().set("align-self", "center");

        for(TrafficItem item : trafficItems) {
            trafficInfo.addElement(new TrafficItemBlock(item));
        }

        setAlignItems(Alignment.STRETCH);
        setClassName("page-block-gray");

        add(layout, trafficInfo);
    }
}
