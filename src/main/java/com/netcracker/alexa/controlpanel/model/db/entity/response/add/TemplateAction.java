package com.netcracker.alexa.controlpanel.model.db.entity.response.add;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "action_templates")
public class TemplateAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "action_param",
            joinColumns = @JoinColumn(name = "id_action"),
            inverseJoinColumns = @JoinColumn(name = "id_param")
    )
    private List<URLParam> URLParams = new ArrayList<>();

    public TemplateAction(){}

    public TemplateAction(URLParam URLParam){
        URLParams.add(URLParam);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setURLParams(List<URLParam> URLParams) {
        this.URLParams = URLParams;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public List<URLParam> getURLParams() {
        return URLParams;
    }
}
