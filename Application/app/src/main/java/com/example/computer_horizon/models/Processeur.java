package com.example.computer_horizon.models;

import android.os.Parcel;

import java.io.Serializable;

public class Processeur implements Serializable {

    private String nom;
    private String marque;
    private int nbCoeurs;
    private String frequence;
    private double prix;
    private int qte;
    private String img;
    private int reduction;
    private int cote;
    private String dateCote;
    private double prixReduc;

    public Processeur(String nom, String marque, int nbCoeurs, String frequence, double prix, int qte, String img, int reduction, int cote, String dateCote, double prixReduc) {
        this.nom = nom;
        this.marque = marque;
        this.nbCoeurs = nbCoeurs;
        this.frequence = frequence;
        this.prix = prix;
        this.qte = qte;
        this.img = img;
        this.reduction = reduction;
        this.cote = cote;
        this.dateCote = dateCote;
        this.prixReduc = prixReduc;
    }

    public Processeur(Parcel in) {
        nom = in.readString();
        marque = in.readString();
        nbCoeurs = in.readInt();
        frequence = in.readString();
        prix = in.readDouble();
        qte = in.readInt();
        img = in.readString();
        reduction = in.readInt();
        cote = in.readInt();
        dateCote = in.readString();
        prixReduc = in.readDouble();
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

    public int getNbCoeurs() {
        return nbCoeurs;
    }

    public void setNbCoeurs(int nbCoeurs) {
        this.nbCoeurs = nbCoeurs;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

    public String getDateCote() {
        return dateCote;
    }

    public void setDateCote(String dateCote) {
        this.dateCote = dateCote;
    }

    public double getPrixReduc() {
        return prixReduc;
    }

    public void setPrixReduc(double prixReduc) {
        this.prixReduc = prixReduc;
    }

    @Override
    public String toString() {
        return "Processeur{" +
                "nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", nbCoeurs=" + nbCoeurs +
                ", frequence='" + frequence + '\'' +
                ", prix=" + prix +
                ", qte=" + qte +
                ", img='" + img + '\'' +
                ", reduction=" + reduction +
                ", cote=" + cote +
                ", dateCote='" + dateCote + '\'' +
                ", prixReduc=" + prixReduc +
                '}';
    }
}
