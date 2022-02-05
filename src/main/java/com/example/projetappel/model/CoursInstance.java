package com.example.projetappel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CoursInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cours cours;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Groupe groupe;

    @OneToOne
    private FicheAppel ficheAppel;

    public CoursInstance() {}

    public CoursInstance(Date dateDebut, Date dateFin, Cours cours, Enseignant enseignant, Groupe groupe, FicheAppel ficheAppel) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cours = cours;
        this.enseignant = enseignant;
        this.groupe = groupe;
        this.ficheAppel = ficheAppel;
    }

}
