package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "location_name")
    private String locationName;

    public String getLocationName() {
        return locationName;
    }
}
