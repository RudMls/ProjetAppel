package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Fichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String driveId;

    @Column(nullable = false)
    private String nom;

    private String type;

    public Fichier() {}

    public Fichier(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
