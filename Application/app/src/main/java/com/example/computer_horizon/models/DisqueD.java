package com.example.computer_horizon.models;

import java.io.Serializable;

public class DisqueD implements Serializable {

    private String nom;
    private String marque;
    private String capacite;
    private boolean ssd;
    private double prix;
    private boolean interne;
    private int qte;
    private String img;
    private double prixReduc;

    public DisqueD(String nom, String marque, String capacite, boolean ssd, double prix, boolean interne, int qte, String img, double prixReduc) {
        this.nom = nom;
        this.marque = marque;
        this.capacite = capacite;
        this.ssd = ssd;
        this.prix = prix;
        this.interne = interne;
        this.qte = qte;
        this.img = img;
        this.prixReduc = prixReduc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public boolean isSsd() {
        return ssd;
    }

    public void setSsd(boolean ssd) {
        this.ssd = ssd;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrixReduc() {
        return prixReduc;
    }

    public void setPrixReduc(double prixReduc) {
        this.prixReduc = prixReduc;
    }

    @Override
    public String toString() {
        return "DisqueD{" +
                "nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", capacite='" + capacite + '\'' +
                ", ssd=" + ssd +
                ", prix=" + prix +
                ", interne=" + interne +
                ", qte=" + qte +
                ", img='" + img + '\'' +
                ", prixReduc=" + prixReduc +
                '}';
    }
}
