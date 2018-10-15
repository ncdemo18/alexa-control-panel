package com.netcracker.alexa.controlpanel.model.db.entity.response.handle;

import com.netcracker.alexa.controlpanel.model.db.entity.response.add.TemplateAction;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class ActionURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    private String url;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_template", updatable = false)
    private TemplateAction templateAction;

    public ActionURL(){}

    public ActionURL(String url, TemplateAction templateAction){
        this.url = url;
        this.templateAction = templateAction;
    }

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTemplateAction(TemplateAction templateAction) {
        this.templateAction = templateAction;
    }

    public TemplateAction getTemplateAction() {
        return templateAction;
    }
}
