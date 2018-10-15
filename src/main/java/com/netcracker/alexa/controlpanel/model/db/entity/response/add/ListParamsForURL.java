package com.netcracker.alexa.controlpanel.model.db.entity.response.add;

import javax.persistence.*;

@Entity
@Table(name = "action_param")
public class ListParamsForURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
