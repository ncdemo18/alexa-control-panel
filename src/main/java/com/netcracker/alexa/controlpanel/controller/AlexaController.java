package com.netcracker.alexa.controlpanel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlexaController {

    @GetMapping("/hello")
    String get(){
        return "";
    }
}
