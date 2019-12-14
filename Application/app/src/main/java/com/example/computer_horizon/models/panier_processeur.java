package com.example.computer_horizon.models;

public class panier_processeur {

    private int id;
    private String nom;

    public panier_processeur(int id, String nom) {
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
        return "panier_processeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
