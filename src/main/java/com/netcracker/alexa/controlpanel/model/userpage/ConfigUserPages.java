package com.netcracker.alexa.controlpanel.model.userpage;

public class ConfigUserPages {
    private String username;
    private String location;
    private String ticketPanel;
    private String countTicket;
    private UserPage [] infoPages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserPage[] getInfoPages() {
        return infoPages;
    }

    public void setInfoPages(UserPage[] infoPages) {
        this.infoPages = infoPages;
    }

    public String getTicketPanel() {
        return ticketPanel;
    }

    public void setTicketPanel(String ticketPanel) {
        this.ticketPanel = ticketPanel;
    }

    public String getCountTicket() {
        return countTicket;
    }

    public void setCountTicket(String countTicket) {
        this.countTicket = countTicket;
    }
}
