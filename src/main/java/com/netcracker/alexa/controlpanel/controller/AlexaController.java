package com.netcracker.alexa.controlpanel.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AlexaController {

    @GetMapping("/hello")
    String get(){
        return "";
    }

    @PostMapping("/handle_user_request")
    @ResponseBody
    String handleRequest(@RequestParam("userMessage") String message){
        return "User message: " + message + "!";
    }
}
