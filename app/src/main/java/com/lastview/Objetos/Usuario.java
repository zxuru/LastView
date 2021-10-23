package com.lastview.Objetos;

public class Usuario {
    private Integer ID;
    private String username;
    private String password;

    public Usuario() {
    }

    public Usuario(Integer ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
