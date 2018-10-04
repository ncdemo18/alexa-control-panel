package com.netcracker.alexa.controlpanel.model.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true)
    private String username;

    @Column(name = "count_tickets")
    private int countTickets;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_page",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_page")
    )
    private List<Page> pages = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public int getCountTickets() {
        return countTickets;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Page> getPages() {
        return pages;
    }
}
