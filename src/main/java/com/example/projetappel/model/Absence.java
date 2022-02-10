package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean vu;

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

    public int getId() {
        return id;
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
    public Absence(int id, Etudiant etudiant, FicheAppel ficheAppel) {
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
        this.id=id;
    }
    public Absence(int id, Etudiant etudiant, FicheAppel ficheAppel, Justificatif justificatif) {
        this.etudiant = etudiant;
        this.ficheAppel = ficheAppel;
        this.id=id;
        this.justificatif=justificatif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVu() {
        return vu;
    }

    public void setVu(boolean vu) {
        this.vu = vu;
    }
}
