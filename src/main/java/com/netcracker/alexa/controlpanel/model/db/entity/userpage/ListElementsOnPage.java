package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;

@Entity
@Table(name = "page_element")
public class ListElementsOnPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
