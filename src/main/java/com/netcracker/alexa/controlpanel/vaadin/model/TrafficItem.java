package com.netcracker.alexa.controlpanel.vaadin.model;

public class TrafficItem {
    private String elementA;
    private String elementE;
    private String delay;

    public TrafficItem(String elementA, String elementE, String delay) {
        this.elementA = elementA;
        this.elementE = elementE;
        this.delay = delay;
    }

    public TrafficItem() {
    }

    public String getElementA() {
        return elementA;
    }

    public void setElementA(String elementA) {
        this.elementA = elementA;
    }

    public String getElementE() {
        return elementE;
    }

    public void setElementE(String elementE) {
        this.elementE = elementE;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}
