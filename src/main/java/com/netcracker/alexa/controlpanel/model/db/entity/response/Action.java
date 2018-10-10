package com.netcracker.alexa.controlpanel.model.db.entity.response;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
/*
    @Column(name = "type")
    private String typeAction;*/

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "action_param",
            joinColumns = @JoinColumn(name = "id_action"),
            inverseJoinColumns = @JoinColumn(name = "id_param")
    )
    private List<Param> params = new ArrayList<>();

   /* public boolean isRequiredAction() {
        return !typeAction.equals("nothing");
    }*/

   public Action(){}

   public Action(Param param){
       addParam(param);
   }

   private void addParam(Param param) {
       params.add(param);
   }

    public String getActionURL(String userLogin) {
        StringBuilder url = new StringBuilder("user/" + userLogin);
        for(Param param : params) {
            url.append("/").append(param.getName());
            if(param.getValue() != null && !param.getValue().isEmpty()) {
                url.append("/").append(param.getValue());
            }
        }
        return url.toString();
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
}
