package com.example.projetappel.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
public class Justificatif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String texte;

    @CreationTimestamp
    private Date date;

    private boolean validee = false;

    @ManyToOne
    private Fichier fichier;

    @ManyToOne
    private Scolarite scolarite;

    @OneToMany(
            mappedBy = "justificatif",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Absence> absences = new ArrayList<>();

    public Justificatif() {
    }


    public Justificatif(Fichier fichier) {
        this.fichier = fichier;
    }

    public Justificatif(Fichier fichier, List<Absence> absences) {
        this.fichier = fichier;
        this.absences = absences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValidee() {
        return validee;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    public Fichier getFichier() {
        return fichier;
    }

    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }
}
