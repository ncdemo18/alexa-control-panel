package com.netcracker.alexa.controlpanel.vaadin.model;

public class Weather {
    private String srcImage;

    private String temperature;

    private String location;

    private String comment;

    public Weather() {
    }

    public Weather(String srcImage, String temperature, String location, String comment) {
        this.srcImage = srcImage;
        this.temperature = temperature;
        this.location = location;
        this.comment = comment;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
