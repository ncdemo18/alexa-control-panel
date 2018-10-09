package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String typeAction;

    @Column(name = "user_login")
    private String userLogin;

    //only one param?
    @Column(name = "param")
    private String param;

    public boolean isRequiredAction() {
        return !typeAction.equals("nothing");
    }

    @Override
    public String toString() {
        String result = typeAction;
        if(isRequiredAction()) {
            result = "user/" + userLogin + "/" + typeAction;
            if(param != null && !param.isEmpty()) {
                result += param;
            }
        }
        return result;
    }
}
