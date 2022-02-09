package com.example.projetappel.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "formation")
    private Set<Appartenir> appartenirs = new HashSet<>();

    @OneToMany(mappedBy = "formation")
    private Set<Cours> cours = new HashSet<>();

    public Formation() {
    }

    public Formation(String libelle) {
        this.libelle = libelle;
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

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Appartenir> getAppartenirs() {
        return appartenirs;
    }

    public void setAppartenirs(Set<Appartenir> appartenirs) {
        this.appartenirs = appartenirs;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }
}
