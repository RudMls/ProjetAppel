package com.example.projetappel.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Appartenir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Etudiant etudiant;

    @ManyToOne
    private Groupe groupe;

    @ManyToOne
    private Formation formation;

    public Appartenir() {
    }

    public Appartenir( Etudiant etudiant, Groupe groupe, Formation formation) {
        this.etudiant = etudiant;
        this.groupe = groupe;
        this.formation = formation;
    }

    public Appartenir(int id, Etudiant etudiant, Groupe groupe, Formation formation) {
        this.id = id;
        this.etudiant = etudiant;
        this.groupe = groupe;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
