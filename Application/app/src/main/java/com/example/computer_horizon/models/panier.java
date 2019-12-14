package com.example.computer_horizon.models;

public class panier {

    private int id;
    private String mail;

    public panier(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "panier{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                '}';
    }
}
