package com.netcracker.alexa.controlpanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AddressService {

    @Autowired
    private HttpServletRequest request;

    public String getFullURL(String userLogin, String url) {
        String applicationURL = request.getRequestURL().toString();
        return applicationURL + "user/" + userLogin + "/" + url;
    }
}
