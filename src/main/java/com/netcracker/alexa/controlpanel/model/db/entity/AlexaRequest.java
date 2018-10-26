package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class AlexaRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alexa_phrase", unique = true)
    private String alexaPhrase;

    @Column(name = "search_version")
    private String searchVersion;

    public AlexaRequest(String alexaPhrase, String searchVersion) {
        this.alexaPhrase = alexaPhrase;
        this.searchVersion = searchVersion;
    }

    public AlexaRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlexaPhrase() {
        return alexaPhrase;
    }

    public void setAlexaPhrase(String alexaPhrase) {
        this.alexaPhrase = alexaPhrase;
    }

    public String getSearchVersion() {
        return searchVersion;
    }

    public void setSearchVersion(String searchVersion) {
        this.searchVersion = searchVersion;
    }
}
