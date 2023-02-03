package com.example.gesionetudiant;

import com.google.firebase.database.Exclude;

public class Contact {
    @Exclude
    private long id;
    private String nom;
    private String tel;
    private String email;
    private String classe;
    private String image;

    public Contact(){
    }

    public Contact(String nom, String tel, String email, String classe) {
        this.nom = nom;
        this.tel = tel;
        this.email = email;
        this.classe = classe;
    }

    public long getId() {
        return id;
    }
    @Exclude
    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
