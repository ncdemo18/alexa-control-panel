package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;

/*@Entity
@Table(name = "answer")*/
public class AlexaAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String answerText;
}
