package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*@Entity
@Table(name = "actions")*/
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "action_name")
    private String actionName;

    @ManyToMany
    @JoinTable(name = "action_answer",
            joinColumns = @JoinColumn(name = "id_action"),
            inverseJoinColumns = @JoinColumn(name = "id_answer")
    )
    private List<AlexaAnswer> answers = new ArrayList<>();
}
