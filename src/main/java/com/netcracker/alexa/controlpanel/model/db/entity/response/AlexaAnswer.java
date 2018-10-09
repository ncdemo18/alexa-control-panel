package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;

/*@Entity
@Table(name = "response")*/
public class AlexaAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phrase_request")
    private String phraseRequest;

    @Column(name = "phrase_answer")
    private String phraseAnswer;
}
