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

    @OneToMany (mappedBy = "justificatif")
    private Set<Absence> absences = new HashSet<>();

    public Justificatif() {}

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




}
