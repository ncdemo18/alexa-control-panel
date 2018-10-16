package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.service.GrantTicketCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cases")
public class GrantTicketCaseController {

    @Autowired
    private GrantTicketCaseService grantTicketCaseService;

    @GetMapping("/grant_tickets/next_step/{grant_user}/{receive_user}")
    private String grantTicketNextStep(@PathVariable("grant_user") String grantUser,
                                       @PathVariable("receive_user") String receiveUser){
        return grantTicketCaseService.nextStep(grantUser, receiveUser);
    }
}
