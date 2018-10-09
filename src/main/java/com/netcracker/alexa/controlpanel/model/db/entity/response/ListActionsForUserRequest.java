package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;

@Entity
@Table(name = "list_actions")
public class ListActionsForUserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
