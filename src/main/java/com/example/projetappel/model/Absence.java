package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private FicheAppel ficheAppel;

    @ManyToOne
    private Justificatif justificatif;

    public Absence() {

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

    public Justificatif getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(Justificatif justificatif) {
        this.justificatif = justificatif;
    }

    public Absence(Etudiant etudiant, FicheAppel ficheAppel) {
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
    }
}
