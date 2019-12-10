package com.example.computer_horizon.models;

public class Ordinateur {

    private String nom;
    private String marque;
    private double prix;
    private String nomProc;
    private String nomCG;
    private String capacite;
    private int memoireV;
    private boolean ssd;
    private String description;
    private int qte;
    private String capaciteSsd;
    private String img;
    private double prixReduc;


    public Ordinateur(String nom, String marque, double prix,
                      String nomProc, String nomCG, String capacite,
                      int memoireV, boolean ssd, String description,
                      int qte, String capaciteSsd, String img, double prixReduc) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.nomProc = nomProc;
        this.nomCG = nomCG;
        this.capacite = capacite;
        this.memoireV = memoireV;
        this.ssd = ssd;
        this.description = description;
        this.qte = qte;
        this.capaciteSsd = capaciteSsd;
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

    public String getNomProc() {
        return nomProc;
    }

    public void setNomProc(String nomProc) {
        this.nomProc = nomProc;
    }

    public String getNomCG() {
        return nomCG;
    }

    public void setNomCG(String nomCG) {
        this.nomCG = nomCG;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public int getMemoireV() {
        return memoireV;
    }

    public void setMemoireV(int memoireV) {
        this.memoireV = memoireV;
    }

    public boolean isSsd() {
        return ssd;
    }

    public void setSsd(boolean ssd) {
        this.ssd = ssd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getCapaciteSsd() {
        return capaciteSsd;
    }

    public void setCapaciteSsd(String capaciteSsd) {
        this.capaciteSsd = capaciteSsd;
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
        return "Ordinateur{" +
                "nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                ", nomProc='" + nomProc + '\'' +
                ", nomCG='" + nomCG + '\'' +
                ", capacite='" + capacite + '\'' +
                ", memoireV=" + memoireV +
                ", ssd=" + ssd +
                ", description='" + description + '\'' +
                ", qte=" + qte +
                ", capaciteSsd='" + capaciteSsd + '\'' +
                ", img='" + img + '\'' +
                ", prixReduc=" + prixReduc +
                '}';
    }
}
