package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.netcracker.alexa.controlpanel.vaadin.model.Weather;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("style.css")
public class WeatherBlock extends VerticalLayout {
    private Image image;
    private Label temperature;
    private Label location;
    private Label comment;

    private Weather weather;

    public WeatherBlock(Weather weather) {
        this.weather = weather;

        this.image = new Image(weather.getSrcImage(), weather.getComment());

        this.temperature = new Label(weather.getTemperature());
        this.temperature.addClassName("white-font");
        this.temperature.getStyle().set("font-size", "48px").set("font-weight", "bold");

        this.location = new Label(weather.getLocation());
        this.location.addClassName("white-font");
        this.location.getStyle().set("font-size", "28px");

        this.comment = new Label(weather.getComment());
        this.comment.addClassName("gray-font");
        this.comment.getStyle().set("font-size", "22px");

        setClassName("page-block-gray");

        VerticalLayout weatherImage = new VerticalLayout(image);
        VerticalLayout weatherInfo = new VerticalLayout(this.temperature, this.location, this.comment);
        weatherInfo.getStyle().set("line-height", "150%");
        HorizontalLayout weatherCard = new HorizontalLayout(weatherImage, weatherInfo);
        add(weatherCard);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Label getTemperature() {
        return temperature;
    }

    public void setTemperature(Label temperature) {
        this.temperature = temperature;
    }

    public Label getLocation() {
        return location;
    }

    public void setLocation(Label location) {
        this.location = location;
    }

    public Label getComment() {
        return comment;
    }

    public void setComment(Label comment) {
        this.comment = comment;
    }


}
