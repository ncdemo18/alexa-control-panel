package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "page_element",
            joinColumns = @JoinColumn(name = "id_page"),
            inverseJoinColumns = @JoinColumn(name = "id_page_element")
    )
    private List<Element> elements = new ArrayList<>();

    public List<Element> getElements() {
        return elements;
    }
}
