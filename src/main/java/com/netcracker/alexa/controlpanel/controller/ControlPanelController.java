package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ControlPanelController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    String startPage(){
        return "index";
    }

    @GetMapping("/authorization/{username}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    String authorization(@PathVariable("username") String username){
        System.out.println("authorization: " + username);
        if(userRepository.existsUserByUsername(username)) {
            return "yes";
        }
        return "no";
    }
}
