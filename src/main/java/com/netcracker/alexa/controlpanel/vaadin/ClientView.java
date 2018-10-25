package com.netcracker.alexa.controlpanel.vaadin;

import com.netcracker.alexa.controlpanel.repository.UserRepository;
import com.netcracker.alexa.controlpanel.vaadin.blocks.*;
import com.netcracker.alexa.controlpanel.vaadin.model.NewItem;
import com.netcracker.alexa.controlpanel.vaadin.model.StockItem;
import com.netcracker.alexa.controlpanel.vaadin.model.TrafficItem;
import com.netcracker.alexa.controlpanel.vaadin.model.Weather;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Push
@StyleSheet("style.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@Route("user")
public class ClientView extends Div implements HasUrlParameter<String> {
    private UserRepository userRepository;
    private Registration commandRegistration;

    private UI currentUi;

    VerticalLayout messages = new VerticalLayout();

    private String login;
    private Label label = new Label("User login");

    public ClientView(@Autowired UserRepository userRepository) {
        setClassName("nc-background-image");
        this.userRepository = userRepository;
    }

    private void showPage() {

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        this.login = parameter;
        label.setText(login);

        Weather weather = new Weather("/frontend/img/london/page1/sun.png", "21,5° C", "London, UK", "Mostly Sunny");
        WeatherBlock weatherBlock = new WeatherBlock(weather);

        List<NewItem> itemList = new ArrayList<>();
        itemList.add(new NewItem("/frontend/img/london/page1/news_picture_bridge.png", "UK tourist hand big euro find to police", "12:17 AM, Mar 1st"));
        itemList.add(new NewItem("North Italian city bans cricket", "4:26 PM, Mar 1st"));
        itemList.add(new NewItem("Dutch, Belgium investors show strong interest: Mustapa", "3:43 AM, Feb 28th"));
        itemList.add(new NewItem("Only Belgium breaks ranks in EU unity with Madrid", "1:05 AM, Feb 28th"));
        ListNewsBlock listNewsBlock = new ListNewsBlock(itemList);

        List<StockItem> stockItems = new ArrayList<>();
        stockItems.add(new StockItem("RTSI INDEX", "940,77"));
        stockItems.add(new StockItem("DOWJ", "22775,39"));
        stockItems.add(new StockItem("AAPL", "155,48"));
        ListStocksBlock stocksBlock = new ListStocksBlock(stockItems);

        List<TrafficItem> trafficItems = new ArrayList<>();
        trafficItems.add(new TrafficItem("A1", "E19", "15 min delay"));
        trafficItems.add(new TrafficItem("A3", "E40", "25 min delay"));
        trafficItems.add(new TrafficItem("A4", "E411", "23 min delay"));
        HeavyTrafficBlock heavyTrafficBlock = new HeavyTrafficBlock(trafficItems, "8");

        VerticalLayout firstColumn = new VerticalLayout(weatherBlock, stocksBlock);
        VerticalLayout secondColumn = new VerticalLayout(listNewsBlock);
        VerticalLayout thirdColumn = new VerticalLayout(heavyTrafficBlock);

        HorizontalLayout firstRow = new HorizontalLayout(new LogotypeBlock());
        firstRow.setWidth("100%");
        HorizontalLayout secondRow = new HorizontalLayout(new TimeBlock());
        secondRow.setWidth("100%");
        HorizontalLayout thirdRow = new HorizontalLayout(firstColumn, secondColumn, thirdColumn);
        thirdRow.setWidth("100%");

        VerticalLayout page = new VerticalLayout(firstRow, secondRow, thirdRow);
        page.addClassName("pt-page");
        add(page);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        currentUi = attachEvent.getUI();
        commandRegistration = Broadcaster.register(login, command -> {
            currentUi.access(() -> {
                handleCommand(command);
            });
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        commandRegistration.remove();
        commandRegistration = null;
    }

    private void handleCommand(String command) {
        messages.add(new Span(command));
    }
}
