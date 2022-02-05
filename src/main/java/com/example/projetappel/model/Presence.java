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
}
