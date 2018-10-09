package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;

@Entity
@Table(name = "user_page")
public class UserPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
