package com.restFullApp.H2connected.RestFullApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restFullApp.H2connected.RestFullApp.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;


@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date birthday;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;



    public User() {
    }

    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public User(long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthday=" + birthday + '}';
    }
}
