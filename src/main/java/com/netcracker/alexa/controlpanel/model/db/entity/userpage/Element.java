package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;

@Entity
@Table(name = "elements")
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "param")
    private String param;

    public Element(String blockName, String param) {
        this.blockName = blockName;
        this.param = param;
    }

    public Element() {
    }

    public String getBlockName() {
        return blockName;
    }

    public String getParam() {
        return param;
    }
}
