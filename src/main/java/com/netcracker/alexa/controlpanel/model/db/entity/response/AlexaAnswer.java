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

    @Column(name = "user_login")
    private String userLogin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "list_actions",
            joinColumns = @JoinColumn(name = "id_answer"),
            inverseJoinColumns = @JoinColumn(name = "id_action")
    )
    private List<Action> actions = new ArrayList<>();

    public AlexaAnswer(String phraseRequest, String phraseAnswer, String userLogin) {
        this.phraseRequest = phraseRequest;
        this.phraseAnswer = phraseAnswer;
        this.userLogin = userLogin;
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getPhraseAnswer() {
        return phraseAnswer;
    }

    public String getPhraseRequest() {
        return phraseRequest;
    }

    public String getUserLogin() {
        return userLogin;
    }
}
