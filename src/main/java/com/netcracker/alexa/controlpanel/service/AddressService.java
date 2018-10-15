package com.netcracker.alexa.controlpanel.service;

import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final String applicationURL = "https://alexa-control-panel.herokuapp.com/";

    public String getFullURL(String userLogin, String url) {
        return applicationURL + "user/" + userLogin + "/" + url;
    }
}
