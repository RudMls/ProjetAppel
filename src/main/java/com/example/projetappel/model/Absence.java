package com.example.projetappel.model;

import javax.persistence.*;

@Entity
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private CoursInstance coursInstance;

    @ManyToOne
    private Justificatif justificatif;


}
