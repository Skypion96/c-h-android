package com.example.computer_horizon.models;

public class Panier_disqueDur {

    private int id;
    private String nom;

    public Panier_disqueDur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Panier_disqueDur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
