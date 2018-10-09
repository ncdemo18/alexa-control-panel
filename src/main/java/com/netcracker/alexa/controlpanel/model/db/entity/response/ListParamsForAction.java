package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;

@Entity
@Table(name = "action_param")
public class ListParamsForAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
