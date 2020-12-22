package com.manipal.Model;

public class User {
    private String id;
    private String username;
    private String name;
    private String email;
    private String info;

    public User(String id, String username, String name, String email, String info) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User() {


    }
}
