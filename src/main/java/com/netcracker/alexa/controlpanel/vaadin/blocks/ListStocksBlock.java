package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.vaadin.model.StockItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class ListStocksBlock extends VerticalLayout {
    private Label header = new Label("STOCKS");
    private ListBoxWithBorder<StockBlock> stocks = new ListBoxWithBorder<>();

    public ListStocksBlock(List<StockItem> stockItems) {
        header.addClassName("gray-font");
        header.getStyle().set("font-size", "24px");

        for (StockItem stockItem : stockItems) {
            stocks.addElement(new StockBlock(stockItem));
        }

        setAlignItems(Alignment.STRETCH);
        setClassName("page-block-gray");

        add(header, stocks);
    }
}
