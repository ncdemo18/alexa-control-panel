package com.netcracker.alexa.controlpanel.model.db.entity.response.handle;

import javax.persistence.*;

@Entity
@Table(name = "list_url")
public class ListUrlForAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
