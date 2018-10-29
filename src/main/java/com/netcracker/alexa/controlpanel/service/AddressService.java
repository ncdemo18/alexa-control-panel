package com.netcracker.alexa.controlpanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AddressService {
    @Autowired
    private HttpServletRequest request;

    public String getFullURL(String userLogin, String url) {
        String applicationURL = cutMethodName(request.getRequestURL().toString(), request.getRequestURI());
        return  applicationURL + "/user/" + userLogin + "/" + url;
    }

    private String cutMethodName(String requestURL, String requestURI) {
        return requestURL.substring(0, requestURL.length() - requestURI.length());
    }
}
