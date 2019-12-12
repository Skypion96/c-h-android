package com.example.computer_horizon.models;

import java.io.Serializable;

public class CarteG implements Serializable {

    private String nom;
    private String marque;
    private double prix;
    private String frequence;
    private String memoireVideo;
    private int qte;
    private String img;
    private double prixReduc;

    public CarteG(String nom, String marque, double prix, String frequence, String memoireV, int qte, String img, double prixReduc) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.frequence = frequence;
        this.memoireVideo = memoireV;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getMemoireV() {
        return memoireVideo;
    }

    public void setMemoireV(String memoireV) {
        this.memoireVideo = memoireV;
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
        return "CarteG{" +
                "nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                ", frequence='" + frequence + '\'' +
                ", memoireV=" + memoireVideo +
                ", qte=" + qte +
                ", img='" + img + '\'' +
                ", prixReduc=" + prixReduc +
                '}';
    }
}
