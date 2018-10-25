package com.netcracker.alexa.controlpanel.model.db.entity.response.handle;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "list_url",
            joinColumns = @JoinColumn(name = "id_answer"),
            inverseJoinColumns = @JoinColumn(name = "id_url")
    )
    private List<ActionURL> actions = new ArrayList<>();

    public AlexaAnswer(String userLogin, String phraseRequest, String phraseAnswer) {
        this.userLogin = userLogin;
        this.phraseRequest = phraseRequest;
        this.phraseAnswer = phraseAnswer;
    }

    public void addURL(ActionURL url) {
        actions.add(url);
    }
    public AlexaAnswer() {}

    public String getPhraseAnswer() {
        return phraseAnswer;
    }

    public void setPhraseRequest(String phraseRequest) {
        this.phraseRequest = phraseRequest;
    }

    public void setPhraseAnswer(String phraseRequest) {
        this.phraseAnswer = phraseRequest;
    }

    public String getPhraseRequest() {
        return phraseRequest;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<ActionURL> getActions() {
        return actions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setActions(List<ActionURL> actions) {
        this.actions = actions;
    }

    public void correctUserPhrase(){
        phraseRequest = phraseRequest.toLowerCase().replaceAll("[[^A-Za-zА-Яа-я0-9]\\s]"," ").replaceAll("\\s+", " ").trim();
    }

}
