package com.example.computer_horizon.models;

public class Authenticate {

    private String mail, mdp;

    public Authenticate(String mail, String mdp){
        this.mail = mail;
        this.mdp = mdp;
    }

    public String getMail(){
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    @Override
    public String toString() {
        return "Authenticate{" +
                "mail='" + mail + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
