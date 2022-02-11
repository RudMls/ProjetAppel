package com.example.projetappel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    @OneToMany(
            mappedBy = "cours",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<CoursInstance> coursInstances = new ArrayList<>();

    @ManyToOne
    private Formation formation;

    public Cours() {}

    public Cours(String libelle) {
        this.libelle = libelle;
    }

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

    public List<CoursInstance> getCoursInstances() {
        return coursInstances;
    }

    public void setCoursInstances(List<CoursInstance> coursInstances) {
        this.coursInstances = coursInstances;
    }
}
