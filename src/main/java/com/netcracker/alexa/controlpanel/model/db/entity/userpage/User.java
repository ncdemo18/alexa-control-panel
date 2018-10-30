package com.netcracker.alexa.controlpanel.model.db.entity.userpage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "count_tickets")
    private int countTickets;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_page",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_page")
    )
    private List<Page> pages = new ArrayList<>();

    public User(String firstName, String lastName, String login, int countTickets, int loyaltyPoints, Location location, List<Page> pages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.countTickets = countTickets;
        this.loyaltyPoints = loyaltyPoints;
        this.location = location;
        this.pages = pages;
    }

    public User() {
    }

    public String getLogin() {
        return login;
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

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
