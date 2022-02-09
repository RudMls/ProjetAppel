package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean retard;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private FicheAppel ficheAppel;

    public Presence() {}

    public Presence(Etudiant etudiant, FicheAppel ficheAppel) {
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
    }

    public Presence(boolean retard, Etudiant etudiant, FicheAppel ficheAppel) {
        this.retard = retard;
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public FicheAppel getFicheAppel() {
        return ficheAppel;
    }

    public void setFicheAppel(FicheAppel ficheAppel) {
        this.ficheAppel = ficheAppel;
    }
}
