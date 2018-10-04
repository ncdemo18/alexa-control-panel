package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;

/*@Entity
@Table(name = "alexa_phrases")*/
public class AlexaPhrases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "action_id")
    private Action action;
}
