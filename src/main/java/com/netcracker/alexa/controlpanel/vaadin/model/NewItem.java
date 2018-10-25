package com.netcracker.alexa.controlpanel.vaadin.model;

public class NewItem {
    private String srcImage;

    private String header;

    private String time;

    public NewItem(String srcImage, String header, String time) {
        this.srcImage = srcImage;
        this.header = header;
        this.time = time;
    }

    public NewItem(String header, String time) {
        this.header = header;
        this.time = time;
    }

    public NewItem() {
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
