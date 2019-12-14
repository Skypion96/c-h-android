package com.example.computer_horizon.models;

public class PanierDisqueDur {

    private int id;
    private String nom;

    public PanierDisqueDur(int id, String nom) {
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
        return "PanierDisqueDur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
