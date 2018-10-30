package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class AlexaRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alexa_phrase")
    private String alexaPhrase;

    @Column(name = "search_version")
    private String searchVersion;

    @Column(name = "date")
    private Date date;

    public AlexaRequest(String alexaPhrase, String searchVersion, Date date) {
        this.alexaPhrase = alexaPhrase;
        this.searchVersion = searchVersion;
        this.date = date;
    }

    public AlexaRequest() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
