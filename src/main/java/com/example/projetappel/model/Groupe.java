package com.example.projetappel.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "groupe")
    private Set<Appartenir> appartenirs = new HashSet<>();

    @OneToMany(mappedBy = "groupe")
    private Set<CoursInstance> coursInstances = new HashSet<>();

    public Groupe() {}

    public Groupe(String libelle) {
        this.libelle = libelle;
    }

    public Groupe(int id, String libelle) {
        this.id = id;
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
}

