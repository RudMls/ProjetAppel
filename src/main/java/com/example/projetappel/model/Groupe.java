package com.example.projetappel.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    @ManyToOne
    private Formation formation;

    @OneToMany(
        mappedBy = "groupe",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    private Set<Etudiant> etudiants = new HashSet<>();

    public Groupe() {}

    public Groupe(String libelle, Formation formation) {
        this.libelle = libelle;
        this.formation = formation;
    }

    public Groupe(int id, String libelle, Formation formation) {
        this.id = id;
        this.libelle = libelle;
        this.formation = formation;
    }

}

