package com.restFullApp.H2connected.RestFullApp.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restFullApp.H2connected.RestFullApp.User;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Post(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
