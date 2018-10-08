package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_page")
public class UserPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
