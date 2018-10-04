package com.netcracker.alexa.controlpanel.service;

import org.springframework.stereotype.Service;

@Service
public class GrantTicketCaseService {
    private int currentStep = 0;
    private int countStep = 3;

    private String nameGrantUser;
    private String nameReceiveUser;
    private String [] actions = new String[countStep];

    private void resetStep(String nameGrantUser, String nameReceiveUser) {
        this.nameGrantUser = nameGrantUser;
        this.nameReceiveUser = nameReceiveUser;
        currentStep = 0;
        actions[0] = "/user/" + nameGrantUser + "/set_page/2";
        actions[1] = "/user/" + nameGrantUser + "/show_tickets";
        actions[2] = "/user/" + nameGrantUser + "/grant_ticket/" + nameReceiveUser;
    }

    public String nextStep(String nameGrantUser, String nameReceiveUser){
        if(!isContinue(nameGrantUser, nameReceiveUser)) {
            resetStep(nameGrantUser, nameReceiveUser);
        }
        return "redirect:" + actions[currentStep++];
    }

    private boolean isContinue(String nameGrantUser, String nameReceiveUser) {
        return currentStep != 0 && currentStep != countStep &&
                nameGrantUser.equals(this.nameGrantUser) && nameReceiveUser.equals(this.nameReceiveUser);
    }
}
