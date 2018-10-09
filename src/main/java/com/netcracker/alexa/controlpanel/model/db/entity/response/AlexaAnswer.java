package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
public class AlexaAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phrase_request", unique = true)
    private String phraseRequest;

    @Column(name = "phrase_answer")
    private String phraseAnswer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "list_actions",
            joinColumns = @JoinColumn(name = "id_answer"),
            inverseJoinColumns = @JoinColumn(name = "id_action")
    )
    private List<Action> actions = new ArrayList<>();

    public List<Action> getActions() {
        return actions;
    }

    public String getPhraseAnswer() {
        return phraseAnswer;
    }

    public String getPhraseRequest() {
        return phraseRequest;
    }
}
