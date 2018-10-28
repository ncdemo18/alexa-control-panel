package com.netcracker.alexa.controlpanel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.alexa.controlpanel.model.userpage.ConfigUserPages;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class SaveService {
    public void save(ConfigUserPages userPages, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileOutputStream(path), userPages);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ConfigUserPages upload(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new FileInputStream(path), ConfigUserPages.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
