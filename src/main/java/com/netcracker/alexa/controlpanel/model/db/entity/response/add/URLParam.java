package com.netcracker.alexa.controlpanel.model.db.entity.response.add;

import javax.persistence.*;

@Entity
@Table(name = "url_params")
public class URLParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }
}
