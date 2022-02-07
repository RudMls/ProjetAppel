package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    @ManyToOne
    private Formation formation;

    public Cours() {}

    public Cours(String libelle, Formation formation) {
        this.libelle = libelle;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {this.libelle = libelle;}

    public Formation getFormation() {return formation;}

    public void setFormation(Formation formation) {this.formation = formation;}
}
