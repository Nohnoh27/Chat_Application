package com.example.chat_application;

public class User {
    private String nom=null ;
    private String email=null ;
    private String uid=null ;

    void Constructor(){}

    void Constructor(String nom, String email, String uid){
        this.nom = nom;
        this.email = email;
        this.uid = uid;
    }

    public User(Object nom, Object email, String uid) {
        this.nom = nom;
        this.email = email;
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
