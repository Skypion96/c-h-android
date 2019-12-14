package com.example.computer_horizon.models;

public class Panier {

    private int id;
    private String mail;

    public Panier(int id, String mail) {
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
        return "Panier{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                '}';
    }
}
